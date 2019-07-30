package uk.gov.hmcts.payment.processors;

public interface JobProcessor {
    void process(String serviceToken, String baseURL);
}
