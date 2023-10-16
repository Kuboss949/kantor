import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.TreeMap;


public class XMLParser {
    public static TreeMap<String, BigDecimal> parseXMLFile(String path){
        TreeMap<String, BigDecimal> currencyExchangeRates = new TreeMap<>();
        try {
            DocumentBuilder builder = null;
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();
            NodeList positions = doc.getElementsByTagName("pozycja");


            for (int i = 0; i<positions.getLength(); i++) {
                Element position = (Element) positions.item(i);
                Element code = (Element) position.getElementsByTagName("kod_waluty").item(0);
                Element ConvFactor = (Element) position.getElementsByTagName("przelicznik").item(0);
                Element course = (Element) position.getElementsByTagName("kurs_sredni").item(0);
                BigDecimal decimalCourse = parseToBigDecimal(course.getTextContent(), ConvFactor.getTextContent());
                currencyExchangeRates.put(code.getTextContent(), decimalCourse);
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }


        return currencyExchangeRates;
    }

    private static BigDecimal parseToBigDecimal(String course, String convFactor){
        BigDecimal factor = new BigDecimal(convFactor);
        BigDecimal decimalCourse = new BigDecimal(course.replace(',', '.'));
        return factor.equals(BigDecimal.ONE) ? decimalCourse : decimalCourse.divide(factor);
    }


}
