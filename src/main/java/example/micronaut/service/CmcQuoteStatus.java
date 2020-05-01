package example.micronaut.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.time.LocalDateTime;

@Introspected
public class CmcQuoteStatus {

    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    @JsonProperty("error_code")
    Integer errorCode;

    @JsonProperty("error_message")
    String errorMessage;

    @JsonProperty("elapsed")
    Integer elapsedMs;

    @JsonProperty("credit_count")
    Integer creditCount;

    @Override
    public String toString() {
        return "CmcQuoteStatus{" + "timestamp=" + timestamp + ", errorCode=" + errorCode + ", errorMessage='" + errorMessage + '\'' + ", elapsedMs=" + elapsedMs + ", creditCount=" + creditCount + '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getElapsedMs() {
        return elapsedMs;
    }

    public void setElapsedMs(Integer elapsedMs) {
        this.elapsedMs = elapsedMs;
    }

    public Integer getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(Integer creditCount) {
        this.creditCount = creditCount;
    }
}
