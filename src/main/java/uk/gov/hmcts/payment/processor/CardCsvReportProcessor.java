package uk.gov.hmcts.payment.processor;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CardCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = Logger.getLogger(CardCsvReportProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in CardCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", "Bearer "+serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=CARD");
    }
}
