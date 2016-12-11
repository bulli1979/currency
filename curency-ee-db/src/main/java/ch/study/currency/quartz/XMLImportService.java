package ch.study.currency.quartz;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.qos.logback.classic.Logger;
import ch.study.currency.Currency;
import ch.study.currency.CurrencyData;
import ch.study.currency.data.DAOCurrency;

/**
 * @author Michael Federer
 * @version 0.1
 *
 */

/**
 * The XMLImportService class establishes a connection to the given URL and creates an input stream to it. The target URL
 * data will be readout and saved locally in a Document object. The Document object will be parsed for certain DOM elements
 * and the corresponding values will be used to update the specific Currency instance object.
 *
 */
public class XMLImportService {
	private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.XMLImportService");
	private final String ezbPath = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	
	/**
	 * Imports the currencies from the current day provided in a XML-file from the specific EZB URL. And parses the URL input stream for the available
	 * currencies.
	 */
	public void importXML() {
		URL url;
		try {
			url = new URL(ezbPath);
			URLConnection connection = url.openConnection();
			Document doc = parseXML(connection.getInputStream());
			NodeList cubeNodes = doc.getElementsByTagName("Cube");
			for (int i = 0; i < cubeNodes.getLength(); i++) {
				handleData(cubeNodes.item(i));
			}
			CurrencyData.INSTANCE.setDate(new Date());
		} catch (MalformedURLException e) {
			logger.error("Error in importXML " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Error in importXML " + e.getMessage(), e);
		}

	}

	/**
	* Extracts the values from the given items and updates the specific Currency instances with the values.
	* 
	* @param item	Node interface object. The Node interface is the primary datatype for the entire Document Object Model (DOM). It represents a single node in the document tree.
	*
	* @throws ClassNotFoundException	Thrown when an application tries to load in a class through its string name, but no definition for the class with the specified name could be found.
	* @throws SQLException				An exception that provides information on a database access error or other errors. 
	*/
	private void handleData(Node item) throws ClassNotFoundException, SQLException{
		if (item.hasAttributes()) {
			Node currencyNode = item.getAttributes().getNamedItem("currency");
			if (currencyNode != null) {
				Node rateNote = item.getAttributes().getNamedItem("rate");
				String rateString = rateNote.getNodeValue();
				

				String currencyString = currencyNode.getNodeValue();
				Currency cur = CurrencyData.INSTANCE.getCurrencyByShortName(currencyString);
				if(cur != null){
						cur.setCourse(Double.parseDouble(rateString));
						DAOCurrency.getInstance().instertCurrency(cur);
				}
				
			}
		}
	}
	
	/**
	* Parses the commited URL filestream and creates a Document object.
	* 
	* @param inputStream	InputStream object, which represents a connection to a URL.
	* 
	* @return Document interface object, which represents the entire XML document at the given URL. Conceptually, it is the root of the document tree, and provides the primary access to the document's data. 
	*
	*/
	private Document parseXML(InputStream inputStream) {
		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
			doc = objDocumentBuilder.parse(inputStream);
		} catch (Exception exception) {
			logger.error("Error in parseXML " + exception.getMessage(), exception);
		}

		return doc;
	}
}
