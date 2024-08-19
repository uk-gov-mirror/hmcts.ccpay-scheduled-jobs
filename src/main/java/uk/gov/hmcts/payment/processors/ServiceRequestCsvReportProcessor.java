package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class ServiceRequestCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceRequestCsvReportProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {
        LOG.info("Value in ServiceRequestCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        LOG.info("Report is going to be generated for Service Requests");
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-duplicate-sr-report");
    }
}

