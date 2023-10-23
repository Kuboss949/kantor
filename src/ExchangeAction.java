import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExchangeAction implements ActionListener {
    private JComboBox fromCurrCode;
    private JComboBox toCurrCode;
    private JTextField exchangeValue;
    private JLabel exchangeResult;
    private ExchangeManager exchangeManager;
    private AppPanel parent;

    public ExchangeAction(AppPanel parent) {
        this.parent = parent;
        this.fromCurrCode = parent.getFromCurrCode();
        this.toCurrCode = parent.getToCurrCode();
        this.exchangeValue = parent.getExchangeValue();
        this.exchangeResult = parent.getExchangeResult();
        this.exchangeManager = new ExchangeManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String regex = "^[0-9]+([,.][0-9]{1,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exchangeValue.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(parent, "Please provide proper number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String fromCurrency = (String) fromCurrCode.getSelectedItem();
        String toCurrency = (String) toCurrCode.getSelectedItem();
        String exchangeValueString = exchangeValue.getText().contains(",") ? exchangeValue.getText().replace(",", ".") : exchangeValue.getText();
        BigDecimal exchangeValueDecimal = new BigDecimal(exchangeValueString);
        String result = exchangeManager.calculateExchange(exchangeValueDecimal, fromCurrency, toCurrency).toString() + " " + toCurrency;

        exchangeResult.setText(result);
    }
}
