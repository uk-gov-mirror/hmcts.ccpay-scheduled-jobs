package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UnprocessedPaymentUpdateProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(UnprocessedPaymentUpdateProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in UnprocessedPaymentUpdateProcessor-----:{}",baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .headers(headers)
                .patch("/jobs/unprocessed-payment-update");
    }
    
}
