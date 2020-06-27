package example.micronaut.wallet.impl.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static example.micronaut.wallet.impl.controller.StylesController.PATH;

@Slf4j
@Controller(PATH)
public class StylesController {

    public static final String PATH = "/styles.css";
    public static final String RESOURCE_URI = "/styles.css";

    @Get
    public HttpResponse<String> getStyles() {
        try {
            InputStream inputStream = StylesController.class.getResourceAsStream(RESOURCE_URI);
            String style = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
            return HttpResponse.ok(style).contentType("text/css");
        } catch (Exception e) {
            log.warn("exception", e);
            return HttpResponse.notFound();
        }
    }

}
