package example.micronaut.domain;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Slf4j
@Entity
@ToString
@Table(name = "CMCCOIN")
public class CmcCoinEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Integer cmcId;

    private String name;

    private String symbol;

    private String slug;

    @Column(precision = 36, scale = 18)
    private BigDecimal circulatingSupply;

    @Column(precision = 36, scale = 18)
    private BigDecimal totalSupply;

    @Column(precision = 36, scale = 18)
    private BigDecimal maxSupply;

    private Integer cmcRank;

    private LocalDateTime created;

    private LocalDateTime modified;

    public CmcCoinEntity() {
        // for JPA use
    }

    public CmcCoinEntity(Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        this.cmcId = cmcId;
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.cmcRank = cmcRank;
    }

    @PrePersist
    void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    void preUpate() {
        modified = LocalDateTime.now();
    }

}
