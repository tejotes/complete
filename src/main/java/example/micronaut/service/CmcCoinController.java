package example.micronaut.service;

import example.micronaut.domain.CmcCoinEntity;
import example.micronaut.domain.CmcCoinRepository;
import example.micronaut.domain.CmcCoinSaveCommand;
import example.micronaut.domain.CmcCoinUpdateCommand;
import example.micronaut.domain.CmcQuoteEntity;
import example.micronaut.domain.CmcQuoteRepository;
import example.micronaut.domain.SortingAndOrderArguments;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@Controller("/cmccoins")
public class CmcCoinController {

    private final static Logger logger = LoggerFactory.getLogger(CmcCoinController.class.getName());

    protected final CmcCoinRepository cmcCoinRepository;

    protected final CmcQuoteRepository cmcQuoteRepository;

    public CmcCoinController(CmcCoinRepository cmcCoinRepository, CmcQuoteRepository cmcQuoteRepository) {
        this.cmcCoinRepository = cmcCoinRepository;
        this.cmcQuoteRepository = cmcQuoteRepository;
    }

    @Get("/{id}")
    public CmcCoinEntity show(Long id) {
        return cmcCoinRepository.findById(id).orElse(null);
    }

    @Put("/")
    public HttpResponse update(@Body @Valid CmcCoinUpdateCommand command) {
        int numberOfEntitiesUpdated = cmcCoinRepository.update(command.getId(), command.getCmcId(), command.getName(), command.getSymbol(), command.getSlug(), command.getCirculatingSupply(), command.getTotalSupply(), command.getMaxSupply(), command.getCmcRank());

        return HttpResponse.noContent().header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Post("/quote")
    public HttpResponse quote(@Body @Valid CmcQuoteResponse quoteResponse) {

        log.debug("quoteResponse={}", quoteResponse);

        for (String key : quoteResponse.getData().keySet()) {
            CmcQuoteResult cmcQuoteResult = quoteResponse.getData().get(key);
            CmcCoinEntity cmcCoinEntity = cmcCoinRepository.save(cmcQuoteResult.getId(), cmcQuoteResult.getName(), cmcQuoteResult.getSymbol(), cmcQuoteResult.getSlug(), cmcQuoteResult.getCirculatingSupply(), cmcQuoteResult.getTotalSupply(), cmcQuoteResult.getMaxSupply(), cmcQuoteResult.getCmcRank());

            for (String currencyId : cmcQuoteResult.getQuoteMap().keySet()) {
                CmcQuote cmcQuote = cmcQuoteResult.getQuoteMap().get(currencyId);
                if (cmcQuote != null) {
                    CmcQuoteEntity quoteEntity = cmcQuoteRepository.save(cmcCoinEntity, currencyId, cmcQuote.getPrice(), cmcQuote.getMarketCap(), cmcQuote.getPercentChange1h(), cmcQuote.getPercentChange24h(), cmcQuote.getPercentChange7d(), cmcQuote.getLastUpdated());
                }
            }
        }

        return HttpResponse.noContent();
    }

    @Get(value = "/list{?args*}")
    public List<CmcCoinEntity> list(@Valid SortingAndOrderArguments args) {
        return cmcCoinRepository.findAll(args);
    }

    @Post("/")
    public HttpResponse<CmcCoinEntity> save(@Body @Valid CmcCoinSaveCommand command) {
        logger.info("repo={}, command={}", cmcCoinRepository, command);
        CmcCoinEntity cmcCoinEntity = cmcCoinRepository.save(command.getId(), command.getName(), command.getSymbol(), command.getSlug(), command.getCirculatingSupply(), command.getTotalSupply(), command.getMaxSupply(), command.getCmcRank());

        return HttpResponse.created(cmcCoinEntity).headers(headers -> headers.location(location(cmcCoinEntity.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        cmcCoinRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/cmccoins/" + id);
    }

    protected URI location(CmcCoinEntity cmcCoinEntity) {
        return location(cmcCoinEntity.getId());
    }
}
