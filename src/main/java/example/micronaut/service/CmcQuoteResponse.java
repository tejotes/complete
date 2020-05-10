package example.micronaut.service;

import io.micronaut.core.annotation.Introspected;

import java.util.Map;

@Introspected
public class CmcQuoteResponse {

    private Map<String, CmcQuoteResult> data;

    private CmcQuoteStatus status;

    @Override
    public String toString() {
        return "CmcQuoteResponse{" + "data=" + data + ", status=" + status + '}';
    }

    public Map<String, CmcQuoteResult> getData() {
        return data;
    }

    public void setData(Map<String, CmcQuoteResult> data) {
        this.data = data;
    }

    public CmcQuoteStatus getStatus() {
        return status;
    }

    public void setStatus(CmcQuoteStatus status) {
        this.status = status;
    }
}
