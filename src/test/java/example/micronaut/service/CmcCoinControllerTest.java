package example.micronaut.service;

import example.micronaut.domain.CmcCoin;
import example.micronaut.domain.CmcCoinSaveCommand;
import example.micronaut.domain.CmcCoinUpdateCommand;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class CmcCoinControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void supplyAnInvalidOrderTriggersValidationFailure() {
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/cmccoins/list?order=foo"));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }

    @Test
    public void testFindNonExistingGenreReturns404() {
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/cmccoins/99"));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    void testCoinCrudOperations() {
        List<Integer> coinIdList = new ArrayList<>();

        HttpRequest request = HttpRequest.POST("/cmccoins", new CmcCoinSaveCommand(101, "Test1Coin", "T1C", "T1C", BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10), 101));
        HttpResponse response = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        coinIdList.add(entityId(response));

        request = HttpRequest.POST("/cmccoins", new CmcCoinSaveCommand(102, "Test2Coin", "T2C", "T2C", BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20), 102));
        response = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        Integer id = entityId(response);
        coinIdList.add(id);
        request = HttpRequest.GET("/cmccoins/" + id);

        CmcCoin cmcCoin = client.toBlocking().retrieve(request, CmcCoin.class);
        System.out.println("cmcCoin="+cmcCoin);
        assertEquals("Test2Coin", cmcCoin.getName());

        request = HttpRequest.PUT("/cmccoins", new CmcCoinUpdateCommand(id, "Test-2-Coin","T2C", "T2C", BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20), 102));
        response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

        request = HttpRequest.GET("/cmccoins/" + id);
        cmcCoin = client.toBlocking().retrieve(request, CmcCoin.class);
        assertEquals("Test-2-Coin", cmcCoin.getName());

        request = HttpRequest.GET("/cmccoins/list");
        List<CmcCoin> cmcCoinList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoin.class));

        assertEquals(2, cmcCoinList.size());

        request = HttpRequest.GET("/cmccoins/list?max=1");
        cmcCoinList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoin.class));

        assertEquals(1, cmcCoinList.size());
        assertEquals("Test1Coin", cmcCoinList.get(0).getName());

        request = HttpRequest.GET("/cmccoins/list?max=1&order=desc&sort=name");
        cmcCoinList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoin.class));

        assertEquals(1, cmcCoinList.size());
        assertEquals("Test1Coin", cmcCoinList.get(0).getName());

        request = HttpRequest.GET("/cmccoins/list?max=1&offset=10");
        cmcCoinList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoin.class));

        assertEquals(0, cmcCoinList.size());

        // cleanup:
        for (Integer coinId : coinIdList) {
            request = HttpRequest.DELETE("/cmccoins/" + coinId);
            response = client.toBlocking().exchange(request);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        }
    }

    /**
     * helper method to extrace entity id from response
     *
     * @param response
     * @return
     */
    protected Integer entityId(HttpResponse response) {
        String path = "/cmccoins/";
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Integer.valueOf(value.substring(index + path.length()));
        }
        return null;
    }

}
