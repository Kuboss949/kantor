import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.*;

public class AppPanel extends JPanel implements ActionListener {

    private JComboBox fromCurrCode;
    private JComboBox toCurrCode;
    private JTextField exchangeValue;
    private JLabel exchangeResult;
    private ExchangeManager exchangeManager;

    public AppPanel(String filePath) {
        JLabel jcomp1;
        JLabel jcomp6;
        JLabel jcomp7;
        JLabel jcomp8;
        JButton exchange;

        JLabel jcomp10;

        exchangeManager = new ExchangeManager(filePath);

        jcomp1 = new JLabel ("KANTOR");
        fromCurrCode = new JComboBox (CurrencyDataManager.getInstance(filePath).getCurrencyCodes());
        toCurrCode = new JComboBox (CurrencyDataManager.getInstance(filePath).getCurrencyCodes());
        exchangeValue = new JTextField (20);
        exchange = new JButton ("Wymień");
        jcomp6 = new JLabel ("Otrzymana ilość waluty:");
        jcomp7 = new JLabel ("Wybierz walutę do wymiany:");
        jcomp8 = new JLabel ("Podaj ilość:");
        exchangeResult = new JLabel ("0");
        jcomp10 = new JLabel ("Wybierz walutę, na którą chcesz wymienić");
        exchange.addActionListener(this);


        setPreferredSize (new Dimension (752, 425));
        setLayout (null);


        add (jcomp1);
        add (fromCurrCode);
        add (toCurrCode);
        add (exchangeValue);
        add (exchange);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (exchangeResult);
        add (jcomp10);
        Font labelFont = new Font("Arial", Font.PLAIN, 24);
        jcomp1.setFont(labelFont);

        jcomp1.setBounds (325, 25, 160, 55);
        fromCurrCode.setBounds (145, 120, 155, 45);
        toCurrCode.setBounds (410, 115, 165, 50);
        exchangeValue.setBounds (145, 215, 155, 35);
        exchange.setBounds (285, 295, 130, 45);
        jcomp6.setBounds (410, 180, 165, 35);
        jcomp7.setBounds (145, 85, 155, 25);
        jcomp8.setBounds (145, 180, 100, 25);
        exchangeResult.setBounds (410, 220, 100, 25);
        jcomp10.setBounds (410, 85, 245, 25);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String regex = "^[0-9]+([,.][0-9]{1,2})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exchangeValue.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Please provide proper number.", "Wrong input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String fromCurrency = (String) fromCurrCode.getSelectedItem();
        String toCurrency = (String) toCurrCode.getSelectedItem();
        String exchangeValueString = exchangeValue.getText().contains(",") ? exchangeValue.getText().replace(",", ".") : exchangeValue.getText();
        BigDecimal exchangeValueDecimal = new BigDecimal(exchangeValueString);
        String result = exchangeManager.calculateExchange(exchangeValueDecimal, fromCurrency, toCurrency).toString();

        exchangeResult.setText(result);

    }
}
