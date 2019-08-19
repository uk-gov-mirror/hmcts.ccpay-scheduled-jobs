package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CardCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        System.out.println("Value in CardCsvReportProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", "Bearer "+serviceToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payment_method", "CARD");
        RestAssured.given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers(headers)
                .post(baseURL+"/jobs/email-pay-reports");
    }
}
