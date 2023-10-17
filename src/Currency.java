import java.math.BigDecimal;

public class Currency{
    private String code;
    private BigDecimal exchangeRate;
    public Currency() {
        this.code = "PLN";
        this.exchangeRate = new BigDecimal("1");
    }
    public Currency(String code, BigDecimal exchangeRate) {
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
