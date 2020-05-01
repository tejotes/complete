package example.micronaut.service;

import io.micronaut.core.annotation.Introspected;

import java.util.Map;

@Introspected
public class CmcQuoteResponse {

    private Map<Integer, CmcQuoteResult> data;

    private CmcQuoteStatus status;

    @Override
    public String toString() {
        return "CmcQuoteResponse{" + "data=" + data + ", status=" + status + '}';
    }

    public Map<Integer, CmcQuoteResult> getData() {
        return data;
    }

    public void setData(Map<Integer, CmcQuoteResult> data) {
        this.data = data;
    }

    public CmcQuoteStatus getStatus() {
        return status;
    }

    public void setStatus(CmcQuoteStatus status) {
        this.status = status;
    }
}
