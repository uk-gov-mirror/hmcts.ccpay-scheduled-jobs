package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class JobProcessorFactoryTest {

    private JobProcessorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new JobProcessorFactory();
    }

    @Test
    void createStatusUpdateProcessor_returnsNonNullProcessor() {
        StatusUpdateProcessor processor = (StatusUpdateProcessor) factory.getJobType("status-update");
        assertNotNull(processor);
    }

    @Test
    void createDeadLetterQueueProcessor_returnsNonNullProcessor() {
        DeadLetterQueueProcessor processor = (DeadLetterQueueProcessor) factory.getJobType("dead-letter-queue-process");
        assertNotNull(processor);
    }

    @Test
    void createCardCsvReportProcessor_returnsNonNullProcessor() {
        CardCsvReportProcessor processor = (CardCsvReportProcessor) factory.getJobType("card-csv-report");
        assertNotNull(processor);
    }

    @Test
    void createPbaCsvReportProcessor_returnsNonNullProcessor() {
        PbaCsvReportProcessor processor = (PbaCsvReportProcessor) factory.getJobType("pba-csv-report");
        assertNotNull(processor);
    }

    @Test
    void createPbaFinremWeeklyCsvReportProcessor_returnsNonNullProcessor() {
        PbaFinremWeeklyCsvReportProcessor processor = (PbaFinremWeeklyCsvReportProcessor) factory.getJobType("pba-finrem-weekly-csv-report");
        assertNotNull(processor);
    }

    @Test
    void createRefundNotificationUpdateProcessor_returnsNonNullProcessor() {
        RefundNotificationUpdateProcessor processor = (RefundNotificationUpdateProcessor) factory.getJobType("refund-notifications-job");
        assertNotNull(processor);
    }

    @Test
    void createUnprocessedPaymentUpdateProcessor_returnsNonNullProcessor() {
        UnprocessedPaymentUpdateProcessor processor = (UnprocessedPaymentUpdateProcessor) factory.getJobType("unprocessed-payment-update");
        assertNotNull(processor);
    }

    @Test
    void createDuplicatePaymentProcessor_returnsNonNullProcessor() {
        DuplicatePaymentProcessor processor = (DuplicatePaymentProcessor) factory.getJobType("duplicate-payment-process");
        assertNotNull(processor);
    }

    @Test
    void createProcessor_withInvalidJobType_returnsNull() {
        JobProcessor processor = factory.getJobType("invalid-job-type");
        assertNull(processor);
    }
}