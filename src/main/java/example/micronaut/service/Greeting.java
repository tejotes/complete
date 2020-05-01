package example.micronaut.service;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;

@Introspected
public class Greeting {

    private String text;

    private LocalDateTime timestamp = LocalDateTime.now();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
