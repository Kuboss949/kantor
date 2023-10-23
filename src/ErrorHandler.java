import javax.swing.*;

public class ErrorHandler {
    public static void handleError(Exception e){
        JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
