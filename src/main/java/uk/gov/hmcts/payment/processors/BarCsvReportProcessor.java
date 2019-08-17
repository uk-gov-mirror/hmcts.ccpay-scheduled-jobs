package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BarCsvReportProcessor implements JobProcessor {
    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {
        System.out.println("Value in BarCsvReportProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("service_name", "DIGITAL_BAR");
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}

