package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class ServiceRequestCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceRequestCsvReportProcessor.class.getName());

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {
        String date = LocalDateTime.now().format(formatter);
        LOG.info("Value in ServiceRequestCsvReportProcessor-----"+"BaseURL--------"+baseURL+"Date-----"+date);
        LOG.info("Report is going to be generated for Service Requests");
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-duplicate-sr-report?date="+date);
    }
}

