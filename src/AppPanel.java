import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.*;

public class AppPanel extends JPanel{

    private JComboBox fromCurrCode;
    private JComboBox toCurrCode;
    private JTextField exchangeValue;
    private JLabel exchangeResult;

    public AppPanel() {
        JLabel jcomp1;
        JLabel jcomp6;
        JLabel jcomp7;
        JLabel jcomp8;
        JButton exchange;

        JLabel jcomp10;


        jcomp1 = new JLabel ("MONEY CHANGER");
        fromCurrCode = new JComboBox (CurrencyDataManager.getInstance().getCurrencyCodes());
        toCurrCode = new JComboBox (CurrencyDataManager.getInstance().getCurrencyCodes());
        exchangeValue = new JTextField (20);
        exchange = new JButton ("Exchange");
        jcomp6 = new JLabel ("Value after exchange:");
        jcomp7 = new JLabel ("FROM:");
        jcomp8 = new JLabel ("Amount:");
        exchangeResult = new JLabel ("0");
        jcomp10 = new JLabel ("TO:");
        exchange.addActionListener(new ExchangeAction(this));


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

        jcomp1.setBounds (250, 25, 300, 55);
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
    public static void main(String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new AppPanel());
        frame.pack();
        frame.setVisible (true);
    }

    public JComboBox getFromCurrCode() {
        return fromCurrCode;
    }

    public JComboBox getToCurrCode() {
        return toCurrCode;
    }

    public JTextField getExchangeValue() {
        return exchangeValue;
    }

    public JLabel getExchangeResult() {
        return exchangeResult;
    }
}
