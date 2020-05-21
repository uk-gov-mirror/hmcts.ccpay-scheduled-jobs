package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BarCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(BarCsvReportProcessor.class.getName());
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

