package example.micronaut.wallet.impl.service;

import example.micronaut.domain.CmcQuoteEntity;
import example.micronaut.domain.CmcQuoteRepository;
import example.micronaut.wallet.api.dto.BalanceInfo;
import example.micronaut.wallet.api.service.BalanceQuery;
import example.micronaut.wallet.impl.domain.TransferEntity;
import example.micronaut.wallet.impl.domain.TransferRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Singleton
public class BalanceQueryImpl implements BalanceQuery {

    public static final String CURRENCE_ID = "EUR";

    private final TransferRepository transferRepository;

    private final CmcQuoteRepository cmcQuoteRepository;

    public BalanceQueryImpl(TransferRepository transferRepository, CmcQuoteRepository cmcQuoteRepository) {
        this.transferRepository = transferRepository;
        this.cmcQuoteRepository = cmcQuoteRepository;
    }

    @Override
    public List<BalanceInfo> allBalances() {
        // declare result
        Map<String, BalanceInfo> balanceMap = new HashMap<>(31);

        // get all transfers
        List<TransferEntity> allTransfersList = transferRepository.findAll();

        // accumulate transfers
        for (TransferEntity transferEntity : allTransfersList) {
            BalanceInfo balanceInfo = balanceMap.get(transferEntity.getCoinSymbol());
            if (balanceInfo == null) {
                balanceInfo = new BalanceInfo(transferEntity.getCoinSymbol(), transferEntity.getAmount());
                CmcQuoteEntity currentQuoteEntity = cmcQuoteRepository.getLatestByCoinSymbolAndCurrencyId(balanceInfo.getCoinSymbol(), CURRENCE_ID).orElse(null);
                if (currentQuoteEntity != null) {
                    balanceInfo.addCurrencyValue("EUR", currentQuoteEntity.toQuote());
                }
                balanceMap.put(balanceInfo.getCoinSymbol(), balanceInfo);
            } else {
                balanceInfo.setBalance(balanceInfo.getBalance().add(transferEntity.getAmount()));
            }
        }

        // return result
        return new ArrayList<>(balanceMap.values());
    }

    @Override
    public List<BalanceInfo> balanceListByWalletRefid(String refid) {
        return null;
    }
}
