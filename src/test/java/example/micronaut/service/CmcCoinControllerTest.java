package example.micronaut.service;

import example.micronaut.domain.CmcCoinEntity;
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
        List<Long> coinIdList = new ArrayList<>();

        HttpRequest request = HttpRequest.POST("/cmccoins", new CmcCoinSaveCommand(101, "Test1Coin", "T1C", "T1C", BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10), 101));
        HttpResponse response = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        coinIdList.add(entityId(response));

        request = HttpRequest.POST("/cmccoins", new CmcCoinSaveCommand(102, "Test2Coin", "T2C", "T2C", BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20), 102));
        response = client.toBlocking().exchange(request);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        Long id = entityId(response);
        coinIdList.add(id);
        request = HttpRequest.GET("/cmccoins/" + id);

        CmcCoinEntity cmcCoinEntity = client.toBlocking().retrieve(request, CmcCoinEntity.class);
        System.out.println("cmcCoin=" + cmcCoinEntity);
        assertEquals("Test2Coin", cmcCoinEntity.getName());

        request = HttpRequest.PUT("/cmccoins", new CmcCoinUpdateCommand(id, 102, "Test-2-Coin","T2C", "T2C", BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20), 102));
        response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

        request = HttpRequest.GET("/cmccoins/" + id);
        cmcCoinEntity = client.toBlocking().retrieve(request, CmcCoinEntity.class);
        assertEquals("Test-2-Coin", cmcCoinEntity.getName());

        request = HttpRequest.GET("/cmccoins/list");
        List<CmcCoinEntity> cmcCoinEntityList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoinEntity.class));

        assertEquals(2, cmcCoinEntityList.size());

        request = HttpRequest.GET("/cmccoins/list?max=1");
        cmcCoinEntityList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoinEntity.class));

        assertEquals(1, cmcCoinEntityList.size());
        assertEquals("Test1Coin", cmcCoinEntityList.get(0).getName());

        request = HttpRequest.GET("/cmccoins/list?max=1&order=desc&sort=name");
        cmcCoinEntityList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoinEntity.class));

        assertEquals(1, cmcCoinEntityList.size());
        assertEquals("Test1Coin", cmcCoinEntityList.get(0).getName());

        request = HttpRequest.GET("/cmccoins/list?max=1&offset=10");
        cmcCoinEntityList = client.toBlocking().retrieve(request, Argument.of(List.class, CmcCoinEntity.class));

        assertEquals(0, cmcCoinEntityList.size());

        // cleanup:
        for (Long coinId : coinIdList) {
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
    protected Long entityId(HttpResponse response) {
        String path = "/cmccoins/";
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }

}
