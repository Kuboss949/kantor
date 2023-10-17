import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExchangeManager{
    private CurrencyDataManager currencyDataManager;
    public ExchangeManager(String filePath){
        currencyDataManager = CurrencyDataManager.getInstance(filePath);
    }
    public BigDecimal calculateExchange(BigDecimal value, String fromCurrency, String toCurrency){

        BigDecimal rateFrom;
        BigDecimal rateTo;

        rateFrom = currencyDataManager.getCurrencyExchangeRate(fromCurrency);
        rateTo = currencyDataManager.getCurrencyExchangeRate(toCurrency);
        if (fromCurrency.equals(toCurrency)) {
            return value;
        } else {
            BigDecimal plnValue = value.multiply(rateFrom);
            return plnValue.divide(rateTo, 2, RoundingMode.DOWN);
        }
    }



}
