package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BarCsvReportProcessorTest extends BaseIntegrationTest {
    
    @Test
    void happyPathSucceeds() {
        stubForS2s();
        stubForEmailPayReportsWithService("Digital%20Bar");
        
        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );
        
        String s2sToken = new S2SHelper(configuration).generateToken();
        
        BarCsvReportProcessor processor = new BarCsvReportProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}
