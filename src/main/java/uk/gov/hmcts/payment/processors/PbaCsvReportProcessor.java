package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PbaCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(PbaCsvReportProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {
        headers.put("ServiceAuthorization", "Bearer "+serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=CMC");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=DIVORCE");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=FINREM");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=PROBATE");

       LOG.info("Report is going to be generated for FPL");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=FPL");
    }
}
