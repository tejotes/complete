package example.micronaut.service;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client("https://pro-api.coinmarketcap.com/v1")
public interface CmcClient {

    @Get("/cryptocurrency/quotes/latest")
    CmcQuoteResponse quoteLatest(@Header("X-CMC_PRO_API_KEY") String apiKey, @QueryValue("symbol") String symbolCsv);

    @Get("/cryptocurrency/quotes/latest")
    CmcQuoteResponse quoteLatest(@Header("X-CMC_PRO_API_KEY") String apiKey, @QueryValue("symbol") String symbolCsv, @QueryValue("convert") String currencyCsv);
}
