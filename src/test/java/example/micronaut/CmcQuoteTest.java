package example.micronaut;

import example.micronaut.service.CmcQuoteClient;
import example.micronaut.service.CmcQuoteResponse;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class CmcQuoteTest {

    @Inject
    CmcQuoteClient cmcQuoteClient;

    @Test
    void testQuote() {
        CmcQuoteResponse response = cmcQuoteClient.quote().blockingGet();
        System.out.println("response=" + response);
    }
}
