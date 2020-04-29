package example.micronaut.service;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/greet")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Get("/{name}")
    Greeting greet(String name) {
        return greetingService.greet(name);
    }

}
