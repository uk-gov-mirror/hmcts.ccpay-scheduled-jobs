package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DuplicatePaymentProcessorTest extends BaseIntegrationTest{

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForCardStatusUpdate();

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        DuplicatePaymentProcessorTest processor = new DuplicatePaymentProcessorTest();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }

    private void process(String s2sToken, String payUrl) {
    }


}

