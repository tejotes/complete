package example.micronaut.wallet.api.service;

import example.micronaut.wallet.api.dto.TransferInfo;

import java.util.List;
import java.util.Optional;

public interface TransferQuery {

    Optional<TransferInfo> queryByRefid(String refid);

    List<TransferInfo> listAll();

    List<TransferInfo> listByWalletRefid(String walletRefid);

    List<TransferInfo> listByCoinSymbol(String coinSymbol);

    List<TransferInfo> listByWalletRefidAndCoinSymbol(String walletRefid, String coinSymbol);
}
