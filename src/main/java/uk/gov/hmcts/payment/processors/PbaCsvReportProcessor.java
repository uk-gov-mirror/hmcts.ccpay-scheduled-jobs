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
        LOG.info("Report is going to be generated for CMC");
        // Need to replace service name, once CMC onboarded org id
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Civil Money Claims");

        LOG.info("Report is going to be generated for Divorce");
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Divorce");

        LOG.info("Report is going to be generated for Finrem");
        // Need to replace service name, once Finrem onboarded org id
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=PBA&service_name=Finrem");

        LOG.info("Report is going to be generated for Probate");
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
