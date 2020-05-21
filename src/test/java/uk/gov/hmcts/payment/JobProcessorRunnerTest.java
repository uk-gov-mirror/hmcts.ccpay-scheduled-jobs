package uk.gov.hmcts.payment;

import org.junit.jupiter.api.Test;
import uk.gov.hmcts.payment.processors.BarCsvReportProcessor;
import uk.gov.hmcts.payment.processors.BaseIntegrationTest;
import uk.gov.hmcts.payment.processors.JobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.MockJobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.S2SHelper;

import static org.junit.jupiter.api.Assertions.*;

class JobProcessorRunnerTest extends BaseIntegrationTest {

    @Test
    void runCompletes() {
        stubForS2s();
        stubForEmailPayReportsWithService("DIGITAL_BAR");

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );
        
        assertDoesNotThrow(() -> JobProcessorRunner.run(configuration));
    }
}
