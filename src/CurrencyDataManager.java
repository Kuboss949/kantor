import java.math.BigDecimal;
import java.util.TreeMap;

public class CurrencyDataManager {
    private static CurrencyDataManager currencyDataManager;
    private TreeMap<String, BigDecimal> currencyExchangeRates;
    private CurrencyDataManager(String filePath){
            currencyExchangeRates = XMLParser.parseXMLFile(filePath);
            currencyExchangeRates.put("PLN", new BigDecimal("1"));
    }
    public static CurrencyDataManager getInstance(String filePath){
        if(currencyDataManager == null){
            currencyDataManager = new CurrencyDataManager(filePath);
        }
        return currencyDataManager;
    }

    public String[] getCurrencyCodes(){
        return currencyExchangeRates.keySet().toArray(String[]::new);
    }

    public BigDecimal getCurrencyExchangeRate(String code){

        return currencyExchangeRates.containsKey(code) ?  currencyExchangeRates.get(code) : new BigDecimal("1");
    }
}
