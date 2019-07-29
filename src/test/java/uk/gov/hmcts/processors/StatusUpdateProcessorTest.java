package uk.gov.hmcts.processors;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StatusUpdateProcessorTest {

    private final Map<String, String> headers = new HashMap<>();
    @Before
    public void setUp(){
        headers.put("ServiceAuthorization", "12345");
    }

    @Test
    public void shouldReturnForbiddenForServiceAuthorizationFailure() {
                 given()
                .relaxedHTTPSValidation().baseUri("http://localhost:8080").contentType(ContentType.JSON)
                .headers(headers)
                .when()
                .patch("/jobs/card-payments-status-update")
                .then()
                .statusCode(403);
    }
}
