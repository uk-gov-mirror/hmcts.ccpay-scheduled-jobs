package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PbaFinremWeeklyCsvReportProcessor implements JobProcessor {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        String date = LocalDateTime.now().minusDays(7).format(formatter);
        System.out.println("Value in PbaFinremWeeklyCsvReportProcessor-----"+serviceToken+"BaseURL--------"+baseURL+"Date-----"+date);
        headers.put("ServiceAuthorization", serviceToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("payment_method", "PBA");
        jsonObject.put("service_name", "FINREM");
        jsonObject.put("start_date", date);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}
