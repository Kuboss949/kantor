import java.math.BigDecimal;
import java.util.TreeMap;

public class CurrencyDataManager {
    private static CurrencyDataManager currencyDataManager;
    private final TreeMap<String, Currency> currencyExchangeRates;

    private CurrencyDataManager() {
        currencyExchangeRates = XMLParser.parseXMLFile();
    }

    public static CurrencyDataManager getInstance() {
        if (currencyDataManager == null) {
            currencyDataManager = new CurrencyDataManager();
        }
        return currencyDataManager;
    }

    public String[] getCurrencyCodes() {
        return currencyExchangeRates.keySet().toArray(String[]::new);
    }

    public BigDecimal getCurrencyExchangeRate(String code) {
        return currencyExchangeRates.containsKey(code) ? currencyExchangeRates.get(code).getExchangeRate() : new BigDecimal("1");
    }
}
