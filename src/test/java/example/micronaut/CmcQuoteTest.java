package example.micronaut;

import example.micronaut.service.CmcClient;
import example.micronaut.service.CmcQuoteClient;
import example.micronaut.service.CmcQuoteResponse;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class CmcQuoteTest {

    public static final String CMC_SYMBOL_CSV = "BTC,ETH,XRP,BCH,BSV,BNB,EOS,XLM,LINK,ADA,XMR,TRX,NEO,DASH,MIOTA,ZEC,MKR,BTG,GBYTE,PAY";

    @Inject
    CmcQuoteClient cmcQuoteClient;

    @Inject
    CmcClient cmcclient;

    @Test
    void testQuote() {
        CmcQuoteResponse response = cmcQuoteClient.quote().blockingGet();
        System.out.println("response=" + response);
    }

    @Test
    void testCmc() {
        //        String cmcKey = "XXXXXXXXXXXXXXXXXXXXXXXXX";
        //        CmcQuoteResponse response = cmcclient.quoteLatest(apiKey, CMC_SYMBOL_CSV, "EUR");
        //        System.out.println("Response="+response);
    }
}
