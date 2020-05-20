package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class BarCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = Logger.getLogger(BarCsvReportProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {
        LOG.info("Value in BarCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?service_name=DIGITAL_BAR");
    }
}

