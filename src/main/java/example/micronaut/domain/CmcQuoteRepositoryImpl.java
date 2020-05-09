package example.micronaut.domain;

import example.micronaut.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Singleton
public class CmcQuoteRepositoryImpl implements CmcQuoteRepository {

    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public CmcQuoteRepositoryImpl(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional
    public Optional<CmcQuoteEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CmcQuoteEntity.class, id));
    }

    @Override
    @Transactional
    public CmcQuoteEntity save(CmcCoinEntity cmcCoinEntity, String currencyId, BigDecimal price, BigDecimal marketCap, double pctChangeH1, double pctChangeH24, double pctChangeD7, LocalDateTime lastUpdated) {
        CmcQuoteEntity cmcQuoteEntity = new CmcQuoteEntity(cmcCoinEntity, currencyId, price, marketCap, pctChangeH1, pctChangeH24, pctChangeD7, lastUpdated);
        entityManager.persist(cmcQuoteEntity);
        return cmcQuoteEntity;
    }
}
