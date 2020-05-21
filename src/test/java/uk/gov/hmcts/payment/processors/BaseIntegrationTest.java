package uk.gov.hmcts.payment.processors;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class BaseIntegrationTest {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String DUMMY_BEARER_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGlfZ3ciLCJleHAiOjE1OTAwMjI3OTF9.SSg5q0Tm42G4Pc05_kkqcA02veoXSnUaL9uw2wU3hZrZed6dnusnQDShQuiEzFP96OiLb5AgzNs9ZXV_jW1O4Q";
    private static final String DUMMY_BEARER_TOKEN_WITH_BEARER = "Bearer " + DUMMY_BEARER_TOKEN;
    public static final String S2S_AUTHORIZATION_HEADER = "ServiceAuthorization";

    protected WireMockServer s2sWiremock;
    protected WireMockServer payWiremock;

    @BeforeEach
    public void proxyToWireMock() {

        s2sWiremock = new WireMockServer(options().dynamicPort());
        s2sWiremock.start();

        payWiremock = new WireMockServer(options().dynamicPort());
        payWiremock.start();
    }

    @AfterEach
    public void noMoreWireMock() {
        s2sWiremock.stop();
        s2sWiremock = null;

        payWiremock.stop();
        payWiremock = null;
    }
    
    protected void stubForS2s() {
        s2sWiremock.stubFor(WireMock.post("/lease")
                .withHeader(CONTENT_TYPE, containing(JSON_CONTENT_TYPE))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, "text/plain")
                        .withBody(DUMMY_BEARER_TOKEN)
                )
        );
    }
    
    protected void stubForEmailPayReportsWithService(String serviceName) {
        payWiremock.stubFor(WireMock.post("/jobs/email-pay-reports?service_name=" + serviceName)
                .withHeader(CONTENT_TYPE, containing(JSON_CONTENT_TYPE))
                .withHeader(S2S_AUTHORIZATION_HEADER, equalTo(DUMMY_BEARER_TOKEN_WITH_BEARER))
                .willReturn(aResponse()
                        .withStatus(200)
                )
        );
    }

    protected void stubForEmailPayReports(String paymentMethod, String serviceName) {
        payWiremock.stubFor(WireMock.post("/jobs/email-pay-reports?payment_method=" + paymentMethod +"&service_name=" + serviceName)
                .withHeader(CONTENT_TYPE, containing(JSON_CONTENT_TYPE))
                .withHeader(S2S_AUTHORIZATION_HEADER, equalTo(DUMMY_BEARER_TOKEN_WITH_BEARER))
                .willReturn(aResponse()
                        .withStatus(200)
                )
        );
    }

    protected void stubForEmailPayReports(String paymentMethod, String serviceName, String startDate) {
        payWiremock.stubFor(WireMock.post(String.format("/jobs/email-pay-reports?payment_method=%s&service_name=%s&start_date=%s", paymentMethod, serviceName, startDate))
                .withHeader(CONTENT_TYPE, containing(JSON_CONTENT_TYPE))
                .withHeader(S2S_AUTHORIZATION_HEADER, equalTo(DUMMY_BEARER_TOKEN_WITH_BEARER))
                .willReturn(aResponse()
                        .withStatus(200)
                )
        );
    }

    protected void stubForEmailPayReportsWithPaymentMethod(String paymentMethod) {
        payWiremock.stubFor(WireMock.post("/jobs/email-pay-reports?payment_method=" + paymentMethod)
                .withHeader(CONTENT_TYPE, containing(JSON_CONTENT_TYPE))
                .withHeader(S2S_AUTHORIZATION_HEADER, equalTo(DUMMY_BEARER_TOKEN_WITH_BEARER))
                .willReturn(aResponse()
                        .withStatus(200)
                )
        );
    }
}
