package example.micronaut.wallet.impl.service;

import example.micronaut.wallet.api.dto.TransferInfo;
import example.micronaut.wallet.api.service.TransferQuery;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class TransferQueryImpl implements TransferQuery {

    private final TransferQuery transferQuery;

    public TransferQueryImpl(TransferQuery transferQuery) {
        this.transferQuery = transferQuery;
    }

    @Override
    public Optional<TransferInfo> queryByRefid(String refid) {
        return Optional.empty();
    }

    @Override
    public List<TransferInfo> listAll() {
        return null;
    }

    @Override
    public List<TransferInfo> listByWalletRefid(String walletRefid) {
        return null;
    }

    @Override
    public List<TransferInfo> listByCoinSymbol(String coinSymbol) {
        return null;
    }

    @Override
    public List<TransferInfo> listByWalletRefidAndCoinSymbol(String walletRefid, String coinSymbol) {
        return null;
    }
}
