package example.micronaut.service;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("greeting")
public class GreetingConfiguration {

    private String stem = "Hello";

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }
}
