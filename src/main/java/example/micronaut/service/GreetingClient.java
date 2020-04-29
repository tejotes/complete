package example.micronaut.service;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("/greet")
public interface GreetingClient {

    @Get("/{name}")
    Single<Greeting> greet(String name);
}
