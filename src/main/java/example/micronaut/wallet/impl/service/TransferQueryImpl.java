package example.micronaut.wallet.impl.service;

import example.micronaut.wallet.api.dto.TransferInfo;
import example.micronaut.wallet.api.service.TransferQuery;
import example.micronaut.wallet.impl.domain.TransferEntity;
import example.micronaut.wallet.impl.domain.TransferRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public class TransferQueryImpl implements TransferQuery {

    private final TransferRepository transferRepository;

    public TransferQueryImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public Optional<TransferInfo> queryByRefid(String refid) {
        return transferRepository.findByRefid(refid).map(TransferEntity::toTransferInfo);
    }

    @Override
    public List<TransferInfo> listAll() {
        return transferRepository.findAll().stream() //
                .map(TransferEntity::toTransferInfo) //
                .collect(Collectors.toList());
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
