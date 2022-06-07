package uk.gov.hmcts.payment.processors;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLetterQueueProcessor implements JobProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(DeadLetterQueueProcessor.class.getName());
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in DeadLetterQueueProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .headers(headers)
                .patch("/jobs/dead-letter-queue-process");
    }
}

