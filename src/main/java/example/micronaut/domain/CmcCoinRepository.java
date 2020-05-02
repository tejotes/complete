package example.micronaut.domain;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CmcCoinRepository {

    Optional<CmcCoin> findById(Integer id);

    CmcCoin save(int id, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank);

    void deleteById(@NotNull Integer id);

    List<CmcCoin> findAll(@NotNull SortingAndOrderArguments args);

    int update(@NotNull Integer id, String name, String symbol, String slug, BigDecimal circulatingSupply, BigDecimal totalSupply, BigDecimal maxSupply, Integer cmcRank);
}
