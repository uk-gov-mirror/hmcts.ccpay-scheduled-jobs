package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BarCsvReportProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(BarCsvReportProcessor.class);
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {


        LOG.error("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        System.out.println("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .param("service_name", "DIGITAL_BAR")
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}

