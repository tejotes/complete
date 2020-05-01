package example.micronaut.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class CmcQuote {

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("market_cap")
    private BigDecimal marketCap;

    @JsonProperty("percent_change_1h")
    private Double percent_change_1h;

    @JsonProperty("percent_change_24h")
    private Double percent_change_24h;

    @JsonProperty("percent_change_7d")
    private Double percent_change_7d;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @Override
    public String toString() {
        return "CmcQuote{" + "price=" + price + ", marketCap=" + marketCap + ", percent_change_1h=" + percent_change_1h + ", percent_change_24h=" + percent_change_24h + ", percent_change_7d=" + percent_change_7d + ", lastUpdated='" + lastUpdated + '\'' + '}';
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public Double getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(Double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public Double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(Double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public Double getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(Double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
