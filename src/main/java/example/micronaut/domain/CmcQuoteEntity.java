package example.micronaut.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CMCQUOTE")
public class CmcQuoteEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(scale = 3)
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    private CmcCoinEntity cmcCoin;

    @Column(length = 16, nullable = false)
    private String coinSymbol;

    @Column(length = 16, nullable = false)
    private String currencyId;

    @Column(precision = 16, scale = 8)
    private BigDecimal price;

    @Column(precision = 16, scale = 8)
    private BigDecimal marketCap;

    @Column(precision = 16, scale = 8)
    private double pctChangeH1;

    @Column(precision = 16, scale = 8)
    private double pctChangeH24;

    @Column(precision = 16, scale = 8)
    private double pctChangeD7;

    @Column(scale = 3)
    private LocalDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    private BearbeitungStatus status = BearbeitungStatus.ANGELEGT;

    public CmcQuoteEntity() {
    }

    public CmcQuoteEntity(CmcCoinEntity coinEntity, String currencyId, BigDecimal price, BigDecimal marketCap, Double pctChangeH1, Double pctChangeH24, double pctChangeD7, LocalDateTime lastUpdated) {
        this.cmcCoin = coinEntity;
        this.coinSymbol = coinEntity != null ? coinEntity.getSymbol() : null;
        this.currencyId = currencyId;
        this.price = price;
        this.marketCap = marketCap;
        this.pctChangeH1 = pctChangeH1;
        this.pctChangeH24 = pctChangeH24;
        this.pctChangeD7 = pctChangeD7;
        this.lastUpdated = lastUpdated;
    }

    // USD=CmcQuote{
    // price=6602.60701122,
    // marketCap=113563929433.21645,
    // percent_change_1h=0.988615,
    // percent_change_24h=4.37185,
    // percent_change_7d=-12.1352,
    // lastUpdated='2018-08-09T21:56:28.000Z'},
    //
    // EUR=CmcQuote{price=6666.66666666, marketCap=113563929433.21645, percent_change_1h=0.988615, percent_change_24h=4.37185, percent_change_7d=-12.1352, lastUpdated='2018-08-09T21:56:28.000Z'
}
