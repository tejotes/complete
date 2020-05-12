package example.micronaut.service;

import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class CmcQuoteCronJob {

    public static final String CMC_SYMBOL_CSV = "BTC,ETH,XRP,BCH,BSV,LTC,BNB,EOS,XTZ,XLM,LINK,ADA,LEO,CRO,XMR,TRX,NEO,HT,ETC,DASH,MIOTA,ATOM,XEM,ZEC,MKR,BTG,LSK,REP,GBYTE,PAY";
    public static final String CMC_CURRENCY_CSV = "EUR";
    public static final String CMC_KEY = "7ebeed58-16cd-4ca9-8999-5c9766119608";

    @Inject
    CmcClient cmcClient;

    @Inject
    CmcCoinController cmcCoinController;

    //@Scheduled(cron = "5 0/15 * ? * *")
    public void getCmcQuotes() {
        // start timer
        String vorgang = String.format("CmcQuoteCronJob.getCmcQuotes()");
        log.info("START: {}", vorgang);
        long startMillis = System.currentTimeMillis();

        try {
            // get Quote
            CmcQuoteResponse quoteResponse = cmcClient.quoteLatest(CMC_KEY, CMC_SYMBOL_CSV, CMC_CURRENCY_CSV);
            log.info("{}: CmcQuoteResponse={}", vorgang, quoteResponse);

            // store in DB
            cmcCoinController.quote(quoteResponse);

            // log event
            long durationMillis = System.currentTimeMillis() - startMillis;
            log.info("OK: {}. [duration: {}ms].", vorgang, durationMillis);
        } catch (Exception e) {
            long durationMillis = System.currentTimeMillis() - startMillis;
            String message = String.format("ERROR: %s: %s, [duration: %dms]", vorgang, e.getMessage(), durationMillis);
            log.error(message, e);
        }
    }
}
