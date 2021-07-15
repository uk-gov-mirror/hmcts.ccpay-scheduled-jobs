package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PbaCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(PbaCsvReportProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in PbaCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Specified Money Claims");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Divorce");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Financial Remedy");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Probate");

       LOG.info("Report is going to be generated for FPL");

        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Family Public Law");
    }
}
