package example.micronaut.service;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Greeting {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
