package example.micronaut.domain;

import example.micronaut.ApplicationConfiguration;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class CmcCoinRepositoryImpl implements CmcCoinRepository {

    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public CmcCoinRepositoryImpl(EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional
    public Optional<CmcCoinEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CmcCoinEntity.class, id));
    }

    @Override
    @Transactional
    public CmcCoinEntity save(Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        CmcCoinEntity cmcCoinEntity = new CmcCoinEntity(cmcId, name, symbol, slug, circulatingSupply, totalSupply, maxSupply, cmcRank);
        entityManager.persist(cmcCoinEntity);
        return cmcCoinEntity;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name", "symbol", "slug");

    @Override
    @Transactional
    public List<CmcCoinEntity> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT c FROM CmcCoinEntity as c";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY c." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        TypedQuery<CmcCoinEntity> query = entityManager.createQuery(qlString, CmcCoinEntity.class);
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }

    @Override
    @Transactional
    public int update(@NotNull Long id, Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        return entityManager.createQuery("UPDATE CmcCoinEntity c SET c.cmcId = :cmcId, c.name = :name, c.symbol = :symbol, c.slug = :slug, c.circulatingSupply = :circulatingSupply, c.totalSupply =:totalSupply, c.maxSupply = :maxSupply, c.cmcRank = :cmcRank, c.modified = :cmcModified  where c.id = :id") //
                .setParameter("id", id) //
                .setParameter("cmcId", cmcId) //
                .setParameter("name", name) //
                .setParameter("symbol", symbol) //
                .setParameter("slug", slug) //
                .setParameter("circulatingSupply", circulatingSupply) //
                .setParameter("totalSupply", totalSupply) //
                .setParameter("maxSupply", maxSupply) //
                .setParameter("cmcRank", cmcRank) //
                .setParameter("cmcModified", LocalDateTime.now()) //
                .executeUpdate();
    }
}
