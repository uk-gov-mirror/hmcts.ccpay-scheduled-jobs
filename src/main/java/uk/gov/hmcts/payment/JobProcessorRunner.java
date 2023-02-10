package uk.gov.hmcts.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.hmcts.payment.processors.JobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.processors.S2SHelper;

public class JobProcessorRunner {
    private static final Logger LOG = LoggerFactory.getLogger(JobProcessorRunner.class.getName());
    
    public static void run(JobProcessorConfiguration configuration) {
        String s2sToken = new S2SHelper(configuration).generateToken();
        LOG.info("S2S Token generated");

        String reportName = configuration.getReportName();
        String payUrl = configuration.getPayUrl();
        String refundsUrl = configuration.getRefundsUrl();
        LOG.info("report name {}",reportName);
        LOG.info("refundsUrl {}",refundsUrl);
        if(! reportName.equalsIgnoreCase("refund-notifications-job")) {
            LOG.info("Job {} started, Url: {}", reportName, payUrl);
            new JobProcessorFactory().getJobType(reportName).process(s2sToken, payUrl);
        }
        else {
            LOG.info("Job {} started, Url: {}", reportName, refundsUrl);
            new JobProcessorFactory().getJobType(reportName).process(s2sToken, refundsUrl);
        }
        LOG.info("Job completed successfully");
    }
}
