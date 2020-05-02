package example.micronaut.service;

import example.micronaut.domain.CmcCoin;
import example.micronaut.domain.CmcCoinRepository;
import example.micronaut.domain.CmcCoinSaveCommand;
import example.micronaut.domain.CmcCoinUpdateCommand;
import example.micronaut.domain.SortingAndOrderArguments;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller("/cmccoins")
public class CmcCoinController {

    private final static Logger logger = LoggerFactory.getLogger(CmcCoinController.class.getName());

    protected final CmcCoinRepository cmcCoinRepository;

    public CmcCoinController(CmcCoinRepository cmcCoinRepository) {
        this.cmcCoinRepository = cmcCoinRepository;
    }


    @Get("/{id}")
    public CmcCoin show(Integer id) {
        return cmcCoinRepository.findById(id).orElse(null);
    }

    @Put("/")
    public HttpResponse update(@Body @Valid CmcCoinUpdateCommand command) {
        int numberOfEntitiesUpdated = cmcCoinRepository.update(command.getId(), command.getName(), command.getSymbol(), command.getSlug(), command.getCirculatingSupply(), command.getTotalSupply(), command.getMaxSupply(), command.getCmcRank());

        return HttpResponse.noContent().header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Get(value = "/list{?args*}")
    public List<CmcCoin> list(@Valid SortingAndOrderArguments args) {
        return cmcCoinRepository.findAll(args);
    }

    @Post("/")
    public HttpResponse<CmcCoin> save(@Body @Valid CmcCoinSaveCommand command) {
        logger.info("repo={}, command={}", cmcCoinRepository, command);
        CmcCoin cmcCoin = cmcCoinRepository.save(command.getId(), command.getName(), command.getSymbol(), command.getSlug(), command.getCirculatingSupply(), command.getTotalSupply(), command.getMaxSupply(), command.getCmcRank());

        return HttpResponse.created(cmcCoin).headers(headers -> headers.location(location(cmcCoin.getId())));
    }

    protected URI location(Integer id) {
        return URI.create("/cmccoins/" + id);
    }

    protected URI location(CmcCoin cmcCoin) {
        return location(cmcCoin.getId());
    }
}
