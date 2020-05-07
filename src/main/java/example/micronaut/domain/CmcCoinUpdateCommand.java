package example.micronaut.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Introspected
public class CmcCoinUpdateCommand {

    @NotNull
    private Long id;

    private Integer cmcId;

    private String name;

    private String symbol;

    private String slug;

    private BigDecimal circulatingSupply;

    private BigDecimal totalSupply;

    private BigDecimal maxSupply;

    private Integer cmcRank;

    public CmcCoinUpdateCommand() {
    }

    @Override
    public String toString() {
        return "CmcCoinUpdateCommand{" + "id=" + id + ", name='" + name + '\'' + ", symbol='" + symbol + '\'' + ", slug='" + slug + '\'' + ", circulatingSupply=" + circulatingSupply + ", totalSupply=" + totalSupply + ", maxSupply=" + maxSupply + ", cmcRank=" + cmcRank + '}';
    }

    public CmcCoinUpdateCommand(@NotNull Long id, Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        this.id = id;
        this.cmcId = cmcId;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.cmcRank = cmcRank;
    }

}
