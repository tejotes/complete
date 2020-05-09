package example.micronaut.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Introspected
public class CmcQuote {

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("market_cap")
    private BigDecimal marketCap;

    @JsonProperty("percent_change_1h")
    private Double percentChange1h;

    @JsonProperty("percent_change_24h")
    private Double percentChange24h;

    @JsonProperty("percent_change_7d")
    private Double percentChange7d;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

    @Override
    public String toString() {
        return "CmcQuote{" + "price=" + price + ", marketCap=" + marketCap + ", percent_change_1h=" + percentChange1h + ", percent_change_24h=" + percentChange24h + ", percent_change_7d=" + percentChange7d + ", lastUpdated='" + lastUpdated + '\'' + '}';
    }
}
