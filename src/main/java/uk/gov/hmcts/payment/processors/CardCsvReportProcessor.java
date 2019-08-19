package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class CardCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        System.out.println("Value in CardCsvReportProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", "Bearer "+serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports?payment_method=CARD");
    }
}
