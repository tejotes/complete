package example.micronaut.wallet.api.service;

import example.micronaut.wallet.api.dto.WalletCreateCommand;
import example.micronaut.wallet.api.dto.WalletInfo;

public interface WalletService {

    WalletInfo createWallet(WalletCreateCommand walletCreateCommand);
}
