package example.micronaut.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CmcCoinRepository {

    Optional<CmcCoinEntity> findById(Long id);

    CmcCoinEntity save(Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank);

    void deleteById(@NotNull Long id);

    List<CmcCoinEntity> findAll(@NotNull SortingAndOrderArguments args);

    int update(@NotNull Long id, Integer cmcId, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank);
}
