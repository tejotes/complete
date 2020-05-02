package example.micronaut.domain;

import example.micronaut.ApplicationConfiguration;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CmcCoinRepositoryImpl implements CmcCoinRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;

    public CmcCoinRepositoryImpl(@CurrentSession EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional
    public Optional<CmcCoin> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(CmcCoin.class, id));
    }

    @Override
    @Transactional
//    @Transactional(readOnly = false, timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public CmcCoin save(int id, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        CmcCoin cmcCoin = new CmcCoin(id, name, symbol, slug, circulatingSupply, totalSupply, maxSupply, cmcRank);
        entityManager.persist(cmcCoin);
        return cmcCoin;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Integer id) {
        findById(id).ifPresent(entityManager::remove);
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name", "symbol", "slug");

    @Override
    @Transactional
    public List<CmcCoin> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT c FROM CmcCoin as c";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        TypedQuery<CmcCoin> query = entityManager.createQuery(qlString, CmcCoin.class);
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }

    @Override
    @Transactional
    public int update(@NotNull Integer id, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank) {
        return entityManager.createQuery("UPDATE CmcCoin c SET c.name = :name, c.symbol = :symbol, c.slug = :slug, c.circulatingSupply = :circulatingSupply, c.totalSupply =:totalSupply, c.maxSupply = :maxSupply, c.cmcRank = :cmcRank  where c.id = :id") //
                .setParameter("id", id) //
                .setParameter("name", name) //
                .setParameter("symbol", symbol) //
                .setParameter("slug", slug) //
                .setParameter("circulatingSupply", circulatingSupply) //
                .setParameter("totalSupply", totalSupply) //
                .setParameter("maxSupply", maxSupply) //
                .setParameter("cmcRank", cmcRank) //
                .executeUpdate();
    }
}
