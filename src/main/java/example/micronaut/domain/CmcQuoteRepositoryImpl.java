package example.micronaut.domain;

import example.micronaut.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    public CmcQuoteEntity save(CmcCoinEntity cmcCoinEntity, String currencyId, BigDecimal price, BigDecimal marketCap, Double pctChangeH1, Double pctChangeH24, Double pctChangeD7, LocalDateTime lastUpdated) {
        CmcQuoteEntity cmcQuoteEntity = new CmcQuoteEntity(cmcCoinEntity, currencyId, price, marketCap, pctChangeH1, pctChangeH24, pctChangeD7, lastUpdated);
        entityManager.persist(cmcQuoteEntity);
        return cmcQuoteEntity;
    }

    @Override
    @Transactional
    public Optional<CmcQuoteEntity> getLatestByCoinSymbolAndCurrencyId(String coinSymbol, String currencyId) {
        // create and execute query
        String qlString = "SELECT q FROM CmcQuoteEntity q WHERE q.coinSymbol = :coinSymbol AND q.currencyId = :currencyId ORDER BY q.lastUpdated DESC";
        TypedQuery<CmcQuoteEntity> query = entityManager.createQuery(qlString, CmcQuoteEntity.class) //
                .setParameter("coinSymbol", coinSymbol) //
                .setParameter("currencyId", currencyId) //
                .setFirstResult(0) //
                .setMaxResults(1);

        // return result
        List<CmcQuoteEntity> quoteEntityList = query.getResultList();
        return Optional.ofNullable(quoteEntityList.isEmpty() ? null : quoteEntityList.get(0));
    }
}
