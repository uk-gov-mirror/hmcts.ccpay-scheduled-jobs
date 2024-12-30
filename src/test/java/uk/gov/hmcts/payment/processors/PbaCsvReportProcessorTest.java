package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PbaCsvReportProcessorTest extends BaseIntegrationTest {

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        // Need to replace service name, once CMC onboarded org id
        stubForEmailPayReports("PBA", "Divorce");
        // Need to replace service name, once Finrem onboarded org id
        stubForEmailPayReports("PBA", "Finrem");
        stubForEmailPayReports("PBA", "Probate");
        stubForEmailPayReports("PBA", "Family%20Public%20Law");
        stubForEmailPayReports("PBA", "Family%20Private%20Law");
        stubForEmailPayReports("PBA", "Civil");
        stubForEmailPayReports("PBA", "Immigration%20and%20Asylum%20Appeals");

        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        PbaCsvReportProcessor processor = new PbaCsvReportProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}
