package uk.gov.hmcts.payment.processors;

import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UnprocessedPaymentUpdateProcessorTest extends BaseIntegrationTest {

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForCardStatusUpdate();

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();
        JobProcessorFactory JobProcessorFactory = new JobProcessorFactory();

        JobProcessorFactory.getJobType("unprocessed-payment-update");
        UnprocessedPaymentUpdateProcessor processor = new UnprocessedPaymentUpdateProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));

    }
}