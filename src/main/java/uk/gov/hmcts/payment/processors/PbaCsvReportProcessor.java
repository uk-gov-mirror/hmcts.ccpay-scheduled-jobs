package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PbaCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();

    @Override
    public void process(String serviceToken, String baseURL) {

        System.out.println("Value in PbaCsvReportProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        String [] serviceName = {"CMC","DIVORCE","FINREM","PROBATE"};
        JSONObject jsonObjectCmc = new JSONObject();
        jsonObjectCmc.put("service_name", serviceName[0]);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObjectCmc.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
        JSONObject jsonObjectDivorce = new JSONObject();
        jsonObjectDivorce.put("service_name", serviceName[1]);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObjectDivorce.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
        JSONObject jsonObjectFinRem = new JSONObject();
        jsonObjectFinRem.put("service_name", serviceName[2]);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObjectFinRem.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
        JSONObject jsonObjectProbate = new JSONObject();
        jsonObjectProbate.put("service_name", serviceName[3]);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(jsonObjectProbate.toString())
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}
