package example.micronaut.wallet.impl.controller;

import example.micronaut.domain.CmcQuoteEntity;
import example.micronaut.domain.CmcQuoteRepository;
import example.micronaut.wallet.api.dto.BalanceInfo;
import example.micronaut.wallet.impl.domain.TransferRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static example.micronaut.wallet.impl.controller.BalanceController.PATH;

@Slf4j
@Controller(PATH)
public class BalanceController {

    public static final String CURRENCY_ID = "EUR";

    public static final String PATH = "/balances";

    private final CmcQuoteRepository cmcQuoteRepository;

    private final TransferRepository transferRepository;

    public BalanceController(CmcQuoteRepository cmcQuoteRepository, TransferRepository transferRepository) {
        this.cmcQuoteRepository = cmcQuoteRepository;
        this.transferRepository = transferRepository;
    }

    @Get("/")
    List<BalanceInfo> listAll() {
        return transferRepository.findAllBalances().stream().map(b -> addCurrencyValue(b, CURRENCY_ID)).collect(Collectors.toList());
    }

    private BalanceInfo addCurrencyValue(BalanceInfo balanceInfo, String currencyId) {
        // check null
        if (balanceInfo == null) {
            return null;
        }

        // get currencyValue
        CmcQuoteEntity latestQuoteEUR = cmcQuoteRepository.getLatestByCoinSymbolAndCurrencyId(balanceInfo.getCoinSymbol(), currencyId).orElse(null);

        // add value info
        if (latestQuoteEUR != null) {
            balanceInfo.addCurrencyValue(currencyId, latestQuoteEUR.toQuote());
        }

        // return result
        return balanceInfo;
    }
}
