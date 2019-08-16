package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class StatusUpdateProcessor implements JobProcessor {
    //private static final Logger LOG = LoggerFactory.getLogger(StatusUpdateProcessor.class);
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        //LOG.error("serviceToken in StatusUpdateProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        System.out.println("serviceToken in StatusUpdateProcessor-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", "Bearer "+serviceToken);
        RestAssured.given().relaxedHTTPSValidation().baseUri(baseURL).contentType(ContentType.JSON).headers(headers)
                .patch("/jobs/card-payments-status-update");
    }
}
