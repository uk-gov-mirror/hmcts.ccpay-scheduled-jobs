package uk.gov.hmcts.processors;

public interface JobProcessor {
    void process(String serviceToken, String baseURL);
}
