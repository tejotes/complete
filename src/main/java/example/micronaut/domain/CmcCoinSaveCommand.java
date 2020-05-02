package example.micronaut.domain;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Introspected
public class CmcCoinSaveCommand {

    @NotNull
    private Integer id;

    private String name;

    private String symbol;

    private String slug;

    private BigDecimal circulatingSupply;

    private BigDecimal totalSupply;

    private BigDecimal maxSupply;

    private Integer cmcRank;

    private LocalDateTime created;

    private LocalDateTime modified;

    public CmcCoinSaveCommand() {
    }

    public CmcCoinSaveCommand(@NotNull Integer id, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.cmcRank = cmcRank;
        this.created = created;
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "CmcCoinSaveCommand{" + "id=" + id + ", name='" + name + '\'' + ", symbol='" + symbol + '\'' + ", slug='" + slug + '\'' + ", circulatingSupply=" + circulatingSupply + ", totalSupply=" + totalSupply + ", maxSupply=" + maxSupply + ", cmcRank=" + cmcRank + ", created=" + created + ", modified=" + modified + '}';
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

    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Integer getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(Integer cmcRank) {
        this.cmcRank = cmcRank;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
