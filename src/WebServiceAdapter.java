import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WebServiceAdapter {

    private static WebServiceAdapter webServiceAdapter = null;
    String url;
    private WebServiceAdapter(){
    }

    public static WebServiceAdapter getInstance(){
        if(webServiceAdapter==null){
            webServiceAdapter = new WebServiceAdapter();
        }
        return webServiceAdapter;
    }

    public void downloadFile(String fileUrl, String dest){
        try {
            URL url = new URL(fileUrl);
            try (BufferedInputStream in = new BufferedInputStream(url.openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
