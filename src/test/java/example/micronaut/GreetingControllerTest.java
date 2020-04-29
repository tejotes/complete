package example.micronaut;

import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class GreetingControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void testGreet() {
        String result = client.retrieve("/greet/Fred").blockingFirst();
        Assertions.assertEquals("Hello Fred", result);
    }

}