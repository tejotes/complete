package example.micronaut.wallet.impl.controller;

import example.micronaut.service.CmcQuote;
import example.micronaut.wallet.api.dto.BalanceInfo;
import example.micronaut.wallet.api.service.BalanceQuery;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static example.micronaut.wallet.impl.controller.ViewController.PATH;

@Controller(PATH)
public class ViewController {

    public static final String PATH = "/views";

    @Inject
    BalanceQuery balanceQuery;

    @Get("/")
    public HttpResponse<String> listAll() {
        List<BalanceInfo> balanceInfoList = balanceQuery.allBalances();
        BigDecimal sum = BigDecimal.ZERO;
        StringBuilder result = new StringBuilder("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><link rel=\"stylesheet\" href=\"styles.css\"></head>\n<body>");
        result.append("<h1>Balances " + (new Date()) + "</h1>");
        result.append("<table><tr><th>coin</th><th>Stand [UTC]</th><th>amount</th><th>price [EUR]</th><th>Δ 1h</th><th>Δ 24h</th><th>Δ 7d</th><th>value [EUR]</th></tr>");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (BalanceInfo balanceInfo : balanceInfoList) {
            CmcQuote euroQuote = balanceInfo.getLatestQuoteMap().get("EUR");
            BigDecimal euroPrice = euroQuote.getPrice();
            BigDecimal value = balanceInfo.getBalance().multiply(euroPrice);
            sum = sum.add(value);
            CmcQuote quote = balanceInfo.getLatestQuoteMap().get("EUR");
            result.append(String.format("<tr><td style=\"text-align: left;\">%s</td><td>%s</td><td>%10.8f</td><td>%10.8f</td><td>%6.2f%%</td><td>%6.2f%%</td><td>%6.2f%%</td><td>%10.2f</td></tr>", balanceInfo.getCoinSymbol(), dateFormatter.format(quote.getLastUpdated()), balanceInfo.getBalance(), euroPrice, euroQuote.getPercentChange1h(), euroQuote.getPercentChange24h(), euroQuote.getPercentChange7d(), value));
        }
        result.append(String.format("<tr><th colspan=\"7\">Summe</th><th>%10.2f</th></tr>", sum));
        result.append("</table></body></html>");
        return HttpResponse.ok(result.toString()).contentType(MediaType.TEXT_HTML_TYPE);
    }
}
