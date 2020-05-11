package example.micronaut.wallet.impl.domain;

import example.micronaut.ApplicationConfiguration;
import example.micronaut.wallet.api.dto.WalletCreateCommand;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Singleton
public class WalletRepository {

    private final EntityManager em;
    private final ApplicationConfiguration applicationConfiguration;

    public WalletRepository(EntityManager em, ApplicationConfiguration applicationConfiguration) {
        this.em = em;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Transactional
    public Optional<WalletEntity> findById(Long id) {
        return Optional.ofNullable(em.find(WalletEntity.class, id));
    }

    @Transactional
    public Optional<WalletEntity> findByRefid(String refid) {
        // create and execute query
        String qlString = "SELECT w FROM Wallet w WHERE w.refid = :refid";
        TypedQuery<WalletEntity> query = em.createQuery(qlString, WalletEntity.class)
                .setParameter("refid", refid);

        // return result
        return Optional.ofNullable(query.getSingleResult());
    }

    @Transactional
    public WalletEntity save(WalletCreateCommand walletCreateCommand) {
        WalletEntity walletEntity = new WalletEntity(walletCreateCommand.getRefid(), walletCreateCommand.getName(), walletCreateCommand.getComment());
        em.persist(walletEntity);
        return walletEntity;
    }

    @Transactional
    public List<WalletEntity> findAll() {
        String qlString = "SELECT w FROM Wallet w";
        TypedQuery<WalletEntity> query = em.createQuery(qlString, WalletEntity.class);
        return query.getResultList();
    }

}
