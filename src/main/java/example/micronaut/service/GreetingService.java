package example.micronaut.service;

import javax.inject.Singleton;

@Singleton
public class GreetingService {

    public GreetingService(GreetingConfiguration greetingConfiguration) {
        this.greetingConfiguration = greetingConfiguration;
    }

    private final GreetingConfiguration greetingConfiguration;

    Greeting greet(String name) {
        String text = greetingConfiguration.getStem() + " " + name;
        Greeting greeting = new Greeting();
        greeting.setText(text);
        return greeting;
    }
}
