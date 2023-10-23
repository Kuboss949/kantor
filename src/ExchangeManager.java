import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeManager {
    public BigDecimal calculateExchange(BigDecimal value, String fromCurrency, String toCurrency) {
        BigDecimal rateFrom;
        BigDecimal rateTo;
        rateFrom = CurrencyDataManager.getInstance().getCurrencyExchangeRate(fromCurrency);
        rateTo = CurrencyDataManager.getInstance().getCurrencyExchangeRate(toCurrency);
        if (fromCurrency.equals(toCurrency)) {
            return value;
        } else {
            BigDecimal plnValue = value.multiply(rateFrom);
            return plnValue.divide(rateTo, 2, RoundingMode.DOWN);
        }
    }


}
