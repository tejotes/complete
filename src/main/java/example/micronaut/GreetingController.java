package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/greet")
public class GreetingController {
    @Get("/{name}")
    String greet(String name) {
        return "Hello " + name;
    }

}
