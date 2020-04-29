package example.micronaut;

import example.micronaut.service.GreetingClient;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class GreetingControllerTest {

    @Inject
    GreetingClient client;

    @Test
    void testGreet() {
        String result = client.greet("Fred").blockingGet().getText();
        Assertions.assertEquals("Hello Fred", result);
    }
}