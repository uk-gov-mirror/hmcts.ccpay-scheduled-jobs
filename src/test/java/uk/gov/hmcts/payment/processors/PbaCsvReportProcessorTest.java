package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PbaCsvReportProcessorTest extends BaseIntegrationTest {

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForEmailPayReports("PBA", "CMC");
        stubForEmailPayReports("PBA", "DIVORCE");
        stubForEmailPayReports("PBA", "FINREM");
        stubForEmailPayReports("PBA", "PROBATE");
        stubForEmailPayReports("PBA", "FPL");

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        PbaCsvReportProcessor processor = new PbaCsvReportProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}
