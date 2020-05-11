package example.micronaut.wallet.impl.service;

import example.micronaut.wallet.api.dto.WalletCreateCommand;
import example.micronaut.wallet.api.dto.WalletInfo;
import example.micronaut.wallet.api.service.WalletService;
import example.micronaut.wallet.impl.domain.WalletRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public WalletInfo createWallet(@NonNull WalletCreateCommand walletCreateCommand) {
        return walletRepository.save(walletCreateCommand).toWalletInfo();
    }
}
