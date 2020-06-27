package example.micronaut.wallet.impl.controller;

import example.micronaut.wallet.api.dto.TransferCreateCommand;
import example.micronaut.wallet.api.dto.TransferInfo;
import example.micronaut.wallet.api.service.TransferQuery;
import example.micronaut.wallet.api.service.TransferService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static example.micronaut.wallet.impl.controller.TransferController.PATH;

@Slf4j
@Controller(PATH)
public class TransferController {

    public static final String PATH = "/transfers";

    private final TransferQuery transferQuery;

    private final TransferService transferService;

    public TransferController(TransferQuery transferQuery, TransferService transferService) {
        this.transferQuery = transferQuery;
        this.transferService = transferService;
    }

    @Get("/")
    List<TransferInfo> listAll() {
        return transferQuery.listAll();
    }

    @Get("/{refid}")
    public HttpResponse<TransferInfo> getByRefid(String refid) {
        return transferQuery.queryByRefid(refid).map(t -> HttpResponse.ok(t).headers(headers -> headers.location(location(t.getRefid())))) //
                .orElse(HttpResponse.notFound());
    }

    @Post("/")
    public HttpResponse<TransferInfo> save(@Body @Valid TransferCreateCommand command) {
        TransferInfo transferInfo = transferService.createTransfer(command);
        return HttpResponse.created(transferInfo).headers(headers -> headers.location(location(transferInfo.getRefid())));
    }


    /* helper methods */


    protected URI location(String refid) {
        return URI.create(PATH + "/" + refid);
    }

    protected URI location(TransferInfo transferInfo) {
        return location(transferInfo.getRefid());
    }
}
