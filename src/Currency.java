import java.math.BigDecimal;

public class Currency {
    private String code;
    private BigDecimal exchangeRate;

    public Currency(String code, BigDecimal exchangeRate) {
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

}
