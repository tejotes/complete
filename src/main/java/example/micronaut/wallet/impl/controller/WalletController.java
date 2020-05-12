package example.micronaut.wallet.impl.controller;

import example.micronaut.wallet.api.dto.WalletCreateCommand;
import example.micronaut.wallet.api.dto.WalletInfo;
import example.micronaut.wallet.api.service.WalletQuery;
import example.micronaut.wallet.api.service.WalletService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/wallets")
public class WalletController {

    private final WalletQuery walletQuery;

    private final WalletService walletService;

    public WalletController(WalletQuery walletQuery, WalletService walletService) {
        this.walletQuery = walletQuery;
        this.walletService = walletService;
    }

    @Get("/{refid}")
    public HttpResponse<WalletInfo> getByRefid(String refid) {
        return walletQuery.queryByRefid(refid) //
        .map(w -> HttpResponse.ok(w).headers(headers -> headers.location(location(w.getRefid())))) //
        .orElse(HttpResponse.notFound());
    }

    @Get("/")
    List<WalletInfo> listAll() {
        return walletQuery.listAll();
    }

    @Post("/")
    public HttpResponse<WalletInfo> save(@Body @Valid WalletCreateCommand command) {
        WalletInfo walletInfo = walletService.createWallet(command);
        return HttpResponse.created(walletInfo).headers(headers -> headers.location(location(walletInfo.getRefid())));

    }

    protected URI location(String refid) {
        return URI.create("/wallets/" + refid);
    }

    protected URI location(WalletInfo walletInfo) {
        return location(walletInfo.getRefid());
    }

}