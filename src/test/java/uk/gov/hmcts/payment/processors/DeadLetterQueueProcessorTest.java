package uk.gov.hmcts.payment.processors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadLetterQueueProcessorTest extends BaseIntegrationTest{

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForCardStatusUpdate();

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        DeadLetterQueueProcessor processor = new DeadLetterQueueProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}

