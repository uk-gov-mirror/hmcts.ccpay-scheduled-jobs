package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

public class MockJobFactory {
    JobProcessorFactory JobProcessorFactory = new JobProcessorFactory();
    @Test
    public void ewfund_approved(){
         JobProcessorFactory.getJobType("refund-approved-update-process");
    }

    @Test
    public void refund_notification(){
        JobProcessorFactory.getJobType("refund-notification-update-process");
    }

}
