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
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGlfZ3ciLCJleHAiOjE1OTAwMjI3OTF9.SSg5q0Tm42G4Pc05_kkqcA02veoXSnUaL9uw2wU3hZrZed6dnusnQDShQuiEzFP96OiLb5AgzNs9ZXV_jW1O4Q")
                )
        );
    }
    
    protected void stubForEmailPayReports(String serviceName) {
        payWiremock.stubFor(WireMock.post("/jobs/email-pay-reports?service_name=" + serviceName)
                .withHeader("Content-Type", containing("application/json"))
                .withHeader("ServiceAuthorization", equalTo("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcGlfZ3ciLCJleHAiOjE1OTAwMjI3OTF9.SSg5q0Tm42G4Pc05_kkqcA02veoXSnUaL9uw2wU3hZrZed6dnusnQDShQuiEzFP96OiLb5AgzNs9ZXV_jW1O4Q"))
                .willReturn(aResponse()
                        .withStatus(200)
                )
        );
    }



}
