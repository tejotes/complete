package example.micronaut.wallet.impl.domain;

import example.micronaut.wallet.api.dto.BalanceInfo;
import example.micronaut.wallet.api.dto.TransferCreateCommand;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class TransferRepository {

    private final EntityManager em;

    public TransferRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Optional<TransferEntity> findById(Long id) {
        return Optional.ofNullable(em.find(TransferEntity.class, id));
    }

    @Transactional
    public TransferEntity save(TransferCreateCommand transferCreateCommand) {
        // create and persist entity
        TransferEntity transferEntity = new TransferEntity(transferCreateCommand);
        em.persist(transferEntity);

        // return result
        return transferEntity;
    }

    @Transactional
    public Optional<TransferEntity> findByRefid(String refid) {
        // create and execute query
        String qlString = "SELECT t FROM Transfer t WHERE t.refid = :refid";
        TypedQuery<TransferEntity> query = em.createQuery(qlString, TransferEntity.class)
                .setParameter("refid", refid);

        // return result
        List<TransferEntity> transferEntityList = query.getResultList();
        return Optional.ofNullable(transferEntityList.isEmpty() ? null : transferEntityList.get(0));
    }

    @Transactional
    public List<TransferEntity> findAll() {
        // create and execute query
        String qlString = "SELECT t FROM Transfer t";
        TypedQuery<TransferEntity> query = em.createQuery(qlString, TransferEntity.class);

        // return result
        return query.getResultList();
    }

    @Transactional
    public List<TransferEntity> findByWalletRefid(String walletRefid) {
        // create and execute query
        String qlString = "SELECT t FROM Transfer t WHERE t.walletRefid = :walletRefid";
        TypedQuery<TransferEntity> query = em.createQuery(qlString, TransferEntity.class) //
                .setParameter("walletRefid", walletRefid);

        // return result
        return query.getResultList();
    }

    @Transactional
    public List<TransferEntity> findByWalletCoinSymbol(String coinSymbol) {
        // create and execute query
        String qlString = "SELECT t FROM Transfer t WHERE t.coinSymbol = :coinSymbol";
        TypedQuery<TransferEntity> query = em.createQuery(qlString, TransferEntity.class) //
                .setParameter("coinSymbol", coinSymbol);

        // return result
        return query.getResultList();
    }

    @Transactional
    public List<BalanceInfo> findAllBalances() {
        String qlString = "SELECT new example.micronaut.wallet.api.dto.BalanceInfo(t.coinSymbol, sum(t.amount)) FROM Transfer t group by t.coinSymbol";
        TypedQuery<BalanceInfo> query = em.createQuery(qlString, BalanceInfo.class);

        // return result
        return query.getResultList();
    }

    @Transactional
    public List<BalanceInfo> findBalancesByWalletRefid(String walletRefid) {
        String qlString = "SELECT new example.micronaut.wallet.api.dto.BalanceInfo(t.coinSymbol, sum(t.amount)) FROM Transfer t where t.walletRefid = :walletRefid group by t.coinSymbol";
        TypedQuery<BalanceInfo> query = em.createQuery(qlString, BalanceInfo.class) //
                .setParameter("walletRefid", walletRefid);

        // return result
        return query.getResultList();
    }
}
