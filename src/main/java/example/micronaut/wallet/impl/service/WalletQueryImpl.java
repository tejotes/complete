package example.micronaut.wallet.impl.service;

import example.micronaut.wallet.api.dto.WalletInfo;
import example.micronaut.wallet.api.service.WalletQuery;
import example.micronaut.wallet.impl.domain.WalletEntity;
import example.micronaut.wallet.impl.domain.WalletRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public class WalletQueryImpl implements WalletQuery {

    private final WalletRepository walletRepository;

    public WalletQueryImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<WalletInfo> listAll() {
        return walletRepository.findAll().stream() //
                .map(WalletEntity::toWalletInfo) //
                .collect(Collectors.toList()); //
    }

    @Override
    public Optional<WalletInfo> queryByRefid(String refid) {
        return walletRepository.findByRefid(refid).map(WalletEntity::toWalletInfo);
    }
}
