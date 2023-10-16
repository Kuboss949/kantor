import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.nbp.pl/kursy/xml/lasta.xml";
        String filePath = "data/kursy.xml";
        WebServiceAdapter.getInstance().downloadFile(url, filePath);

        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new AppPanel(filePath));
        frame.pack();
        frame.setVisible (true);
    }
}