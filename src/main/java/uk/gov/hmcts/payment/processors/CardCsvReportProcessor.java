package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class CardCsvReportProcessor implements JobProcessor {
    //private static final Logger LOG = LoggerFactory.getLogger(CardCsvReportProcessor.class);
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

       // LOG.error("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        System.out.println("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .param("payment_method", "CARD")
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}
