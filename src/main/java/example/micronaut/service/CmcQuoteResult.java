package example.micronaut.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Introspected
public class CmcQuoteResult {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("is_active")
    private Integer isActive;

    @JsonProperty("is_fiat")
    private Integer isFiat;

    @JsonProperty("cmc_rank")
    private Integer cmcRank;

    @JsonProperty("num_market_pairs")
    private Integer numMarketPairs;

    @JsonProperty("circulating_supply")
    private BigDecimal circulatingSupply;

    @JsonProperty("total_supply")
    private BigDecimal totalSupply;

    @JsonProperty("market_cap_by_total_supply")
    private BigDecimal marketCapByTotalSupply;

    @JsonProperty("max_supply")
    private BigDecimal maxSupply;

    @JsonProperty("date_added")
    private LocalDateTime dateAdded;

    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;

    @JsonProperty("tags")
    private List<String> tagList;

    @JsonProperty("quote")
    private Map<String, CmcQuote> quoteMap;

    @Override
    public String toString() {
        return "CmcQuoteResult{" + "id=" + id + ", name='" + name + '\'' + ", symbol='" + symbol + '\'' + ", slug='" + slug + '\'' + ", isActive=" + isActive + ", isFiat=" + isFiat + ", cmcRank=" + cmcRank + ", numMarketPairs=" + numMarketPairs + ", circulatingSupply=" + circulatingSupply + ", totalSupply=" + totalSupply + ", marketCapByTotalSupply=" + marketCapByTotalSupply + ", maxSupply=" + maxSupply + ", dateAdded='" + dateAdded + '\'' + ", lastUpdated='" + lastUpdated + '\'' + ", tagList=" + tagList + ", quote=" + quoteMap + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsFiat() {
        return isFiat;
    }

    public void setIsFiat(Integer isFiat) {
        this.isFiat = isFiat;
    }

    public Integer getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(Integer cmcRank) {
        this.cmcRank = cmcRank;
    }

    public Integer getNumMarketPairs() {
        return numMarketPairs;
    }

    public void setNumMarketPairs(Integer numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
    }

    public BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public BigDecimal getMarketCapByTotalSupply() {
        return marketCapByTotalSupply;
    }

    public void setMarketCapByTotalSupply(BigDecimal marketCapByTotalSupply) {
        this.marketCapByTotalSupply = marketCapByTotalSupply;
    }

    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Map<String, CmcQuote> getQuoteMap() {
        return quoteMap;
    }

    public void setQuoteMap(Map<String, CmcQuote> quoteMap) {
        this.quoteMap = quoteMap;
    }
}
