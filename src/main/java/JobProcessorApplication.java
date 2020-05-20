import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.gov.hmcts.payment.processors.S2SHelper;

import static java.lang.String.format;

public class JobProcessorApplication {
    private static final Logger LOG = Logger.getLogger(JobProcessorApplication.class.getName());
    public static void main(String[] args) {
        try {
            JobProcessorConfiguration configuration = new JobProcessorConfiguration();
            
            String s2sToken = new S2SHelper(configuration).generateToken();
            LOG.info("S2S Token generated");
            
            String reportName = configuration.getReportName();
            String payUrl = configuration.getPayUrl();
            
            LOG.info(format("Job %s started, Url: %s", reportName, payUrl));
            new JobProcessorFactory().getJobType(reportName).process(s2sToken, payUrl);
            LOG.info("Job completed successfully");
        } catch(Exception ex) {
            LOG.log(Level.SEVERE,"Job failed", ex);
        }
    }
    

}
