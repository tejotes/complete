package example.micronaut.service;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/cmctest")
public interface CmcQuoteClient {

    @Get
    Single<CmcQuoteResponse> quote();

}
