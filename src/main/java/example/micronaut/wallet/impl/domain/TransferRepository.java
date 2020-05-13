package example.micronaut.wallet.impl.domain;

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
    public Optional<TransferEntity> findByRefid(String refid) {
        // create and execute query
        String qlString = "SELECT t FROM Transfer t WHERE t.refid = :refid";
        TypedQuery<TransferEntity> query = em.createQuery(qlString, TransferEntity.class)
                .setParameter("refid", refid);

        // return result
        List<TransferEntity> transferEntityList = query.getResultList();
        return Optional.ofNullable(transferEntityList.isEmpty() ? null : transferEntityList.get(0));
    }

}
