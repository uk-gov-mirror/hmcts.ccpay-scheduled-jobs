package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PbaCsvReportProcessorTest extends BaseIntegrationTest {

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForEmailPayReports("PBA", "Civil%20Money%20Claims");
        stubForEmailPayReports("PBA", "Divorce");
        stubForEmailPayReports("PBA", "Finrem");
        stubForEmailPayReports("PBA", "Probate");
        stubForEmailPayReports("PBA", "Family%20Public%20Law");

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        PbaCsvReportProcessor processor = new PbaCsvReportProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}
