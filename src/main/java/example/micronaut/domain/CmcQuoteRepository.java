package example.micronaut.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CmcQuoteRepository {
    Optional<CmcQuoteEntity> findById(Long id);

    CmcQuoteEntity save(CmcCoinEntity cmcCoinEntity, String currencyId, BigDecimal price, BigDecimal marketCap, double pctChangeH1, double pctChangeH24, double pctChangeD7, LocalDateTime lastUpdated);
}
