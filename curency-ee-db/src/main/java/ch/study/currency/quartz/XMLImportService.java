package ch.study.currency.quartz;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ch.qos.logback.classic.Logger;
import ch.study.currency.CurrencyData;

public class XMLImportService {
	private Logger logger = (Logger) LoggerFactory.getLogger("chapters.introduction.XMLImportService");

	public void importXML() {
		URL url;
		try {
			url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
			URLConnection connection = url.openConnection();
			Document doc = parseXML(connection.getInputStream());
			NodeList cubeNodes = doc.getElementsByTagName("Cube");
			for (int i = 0; i < cubeNodes.getLength(); i++) {
				Node item = cubeNodes.item(i);
				if (item.hasAttributes()) {
					Node currencyNode = item.getAttributes().getNamedItem("currency");
					if (currencyNode != null) {
						Node rateNote = item.getAttributes().getNamedItem("rate");
						String rateString = rateNote.getNodeValue();
						String currencyString = currencyNode.getNodeValue();
						CurrencyData.INSTANCE.getCurrencyByShortName(currencyString)
								.setCourse(Double.parseDouble(rateString));
					}
				}
			}
			CurrencyData.INSTANCE.setDate(new Date());
			
		} catch (MalformedURLException e) {
			logger.error("Error in importXML " + e.getMessage(), e);
		} catch (Exception e) {
			logger.error("Error in importXML " + e.getMessage(), e);
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
