package example.micronaut.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CMCQUOTE")
public class CmcQuoteEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private CmcCoinEntity cmcCoin;

    private String coinSymbol;

    private String currencyId;

    @Column(precision = 32, scale = 12)
    private BigDecimal price;

    private BigDecimal marketCap;

    private double pctChangeH1;

    private double pctChangeH24;

    private double pctChangeD7;

    private LocalDateTime lastUpdated;


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
