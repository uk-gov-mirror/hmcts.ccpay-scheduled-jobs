package uk.gov.hmcts.payment.processors;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PbaCsvReportProcessor implements JobProcessor {

    private final Map<String, String> headers = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(PbaCsvReportProcessor.class.getName());

    @Override
    public void process(String serviceToken, String baseURL) {

        LOG.info("Value in PbaCsvReportProcessor-----"+"BaseURL--------"+baseURL);
        headers.put("ServiceAuthorization", serviceToken);

        List<String> services = new ArrayList<>();
        services.add("Civil Money Claims");
        services.add("Divorce");
        services.add("Finrem");
        services.add("Probate");
        services.add("Family Public Law");
        services.add("Family Private Law");
        services.add("civil_service");


        services.forEach((String service) -> {
            LOG.info(String.format("Report is going to be generated for %s", service));
            RestAssured.given().relaxedHTTPSValidation()
                    .contentType(ContentType.JSON)
                    .headers(headers)
                    .post(baseURL+(String.format("/jobs/email-pay-reports?payment_method=PBA&service_name=%s", service)));
        });
    }
}
