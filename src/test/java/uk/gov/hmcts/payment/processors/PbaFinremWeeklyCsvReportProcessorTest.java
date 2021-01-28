package uk.gov.hmcts.payment.processors;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PbaFinremWeeklyCsvReportProcessorTest extends BaseIntegrationTest {

    @Test
    void happyPathSucceeds() {
        stubForS2s();
        String date = LocalDateTime.now().minusDays(7).format(PbaFinremWeeklyCsvReportProcessor.formatter);
        stubForEmailPayReports("PBA", "Financial%20Remedy", date);


        JobProcessorConfiguration configuration = new MockJobProcessorConfiguration(
                "http://localhost:" + s2sWiremock.port(),
                "http://localhost:" + payWiremock.port()
        );

        String s2sToken = new S2SHelper(configuration).generateToken();

        PbaFinremWeeklyCsvReportProcessor processor = new PbaFinremWeeklyCsvReportProcessor();
        assertDoesNotThrow(() -> processor.process(s2sToken, configuration.getPayUrl()));
    }
}
