package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StatusUpdateProcessorTest {

    private final Map<String, String> headers = new HashMap<>();
    @Before
    public void setUp(){
        headers.put("ServiceAuthorization", "12345");
    }

    @Test
    public void shouldReturnForbiddenForServiceAuthorizationFailure() {
                RestAssured.given()
                .relaxedHTTPSValidation().baseUri("http://localhost:8080").contentType(ContentType.JSON)
                .headers(headers)
                .when()
                .patch("/jobs/card-payments-status-update")
                .then()
                .statusCode(403);
    }
}
