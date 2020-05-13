package uk.gov.hmcts.payment.processor;

public interface JobProcessor {
    void process(String serviceToken, String baseURL);
}
