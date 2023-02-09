package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

public class MockJobFactory {
    JobProcessorFactory JobProcessorFactory = new JobProcessorFactory();
 
    @Test
    public void refund_notification(){
        JobProcessorFactory.getJobType("refund-notifications-job");
    }

}
