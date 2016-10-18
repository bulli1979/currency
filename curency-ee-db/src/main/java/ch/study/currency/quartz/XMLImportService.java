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

public class XMLImportService {
	private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.XMLImportService");
	private final String ezbPath = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
	
	public void importXML() {
		URL url;
		try {
			url = new URL(ezbPath);
			URLConnection connection = url.openConnection();
			Document doc = parseXML(connection.getInputStream());
			NodeList cubeNodes = doc.getElementsByTagName("Cube");
			System.out.println("h1");
			for (int i = 0; i < cubeNodes.getLength(); i++) {
				System.out.println("h2");
				handleData(cubeNodes.item(i));
			}
			CurrencyData.INSTANCE.setDate(new Date());
		} catch (MalformedURLException e) {
			logger.error("Error in importXML " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Error in importXML " + e.getMessage(), e);
		}

	}

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
