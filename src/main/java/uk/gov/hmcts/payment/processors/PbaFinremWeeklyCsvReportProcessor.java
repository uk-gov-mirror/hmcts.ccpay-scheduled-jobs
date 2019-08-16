package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class PbaFinremWeeklyCsvReportProcessor implements JobProcessor {

    //private static final Logger LOG = LoggerFactory.getLogger(PbaFinremWeeklyCsvReportProcessor.class);
    private final Map<String, String> headers = new HashMap<>();
    @Override
    public void process(String serviceToken, String baseURL) {


        //LOG.error("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        String date = String.valueOf(LocalDateTime.now().minusDays(7));
        System.out.println("Value in Impl-----"+serviceToken+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);
        RestAssured.given().relaxedHTTPSValidation()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .param("payment_method", "PBA")
                .param("service_name", "FINREM")
                .param("start_date", date)
                .headers(headers)
                .post("/jobs/email-pay-reports");
    }
}
