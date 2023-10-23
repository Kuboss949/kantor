import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.TreeMap;


public class XMLParser {
    public static TreeMap<String, Currency> parseXMLFile() {
        TreeMap<String, Currency> currencyExchangeRates = new TreeMap<>();
        Document doc = WebServiceAdapter.getInstance().downloadFile();
        doc.getDocumentElement().normalize();
        NodeList positions = doc.getElementsByTagName("pozycja");


        for (int i = 0; i < positions.getLength(); i++) {
            Element position = (Element) positions.item(i);
            Element code = (Element) position.getElementsByTagName("kod_waluty").item(0);
            Element ConvFactor = (Element) position.getElementsByTagName("przelicznik").item(0);
            Element course = (Element) position.getElementsByTagName("kurs_sredni").item(0);
            BigDecimal decimalCourse = parseToBigDecimal(course.getTextContent(), ConvFactor.getTextContent());
            currencyExchangeRates.put(code.getTextContent(), new Currency(code.getTextContent(), decimalCourse));
        }
        currencyExchangeRates.put("PLN", new Currency("PLN", new BigDecimal("1")));

        return currencyExchangeRates;
    }

    private static BigDecimal parseToBigDecimal(String course, String convFactor) {
        BigDecimal factor = new BigDecimal(convFactor);
        BigDecimal decimalCourse = new BigDecimal(course.replace(',', '.'));
        return factor.equals(BigDecimal.ONE) ? decimalCourse : decimalCourse.divide(factor);
    }


}
