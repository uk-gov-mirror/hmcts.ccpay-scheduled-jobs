package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class StatusUpdateProcessor implements JobProcessor {
    private static final Logger LOG = Logger.getLogger(StatusUpdateProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in StatusUpdateProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .headers(headers)
                .patch("/jobs/card-payments-status-update");
    }
}
