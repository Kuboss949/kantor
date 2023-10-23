import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WebServiceAdapter {

    private static WebServiceAdapter webServiceAdapter = null;
    private WebServiceAdapter(){
    }

    public static WebServiceAdapter getInstance(){
        if(webServiceAdapter==null){
            webServiceAdapter = new WebServiceAdapter();
        }
        return webServiceAdapter;
    }

    public Document downloadFile(){
        String fileUrl = "https://www.nbp.pl/kursy/xml/lasta.xml";
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(fileUrl);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            ErrorHandler.handleError(e);
            System.exit(1);
        }
        return doc;
    }

}
