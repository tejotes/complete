package example.micronaut.wallet.api.service;

import example.micronaut.wallet.api.dto.BalanceInfo;

import java.util.List;

public interface BalanceQuery {

    List<BalanceInfo> allBalances();

    List<BalanceInfo> balanceListByWalletRefid(String refid);
}
