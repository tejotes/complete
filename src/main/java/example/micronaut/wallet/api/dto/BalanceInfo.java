package example.micronaut.wallet.api.dto;

import example.micronaut.service.CmcQuote;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Introspected
public class BalanceInfo {

    public BalanceInfo(String coinSymbol, BigDecimal balance) {
        this.coinSymbol = coinSymbol;
        this.balance = balance;
        this.latestQuoteMap = new HashMap<>();
    }

    public void addCurrencyValue(@NonNull String currencyId, CmcQuote latestQuote) {
        this.latestQuoteMap.put(currencyId, latestQuote);
    }

    private String coinSymbol;

    private BigDecimal balance;

    private Map<String, CmcQuote> latestQuoteMap;
}
