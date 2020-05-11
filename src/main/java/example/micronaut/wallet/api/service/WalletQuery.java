package example.micronaut.wallet.api.service;

import example.micronaut.wallet.api.dto.WalletInfo;

import java.util.List;
import java.util.Optional;

public interface WalletQuery {

    List<WalletInfo> listAll();

    Optional<WalletInfo> queryByRefid(String refid);
}
