import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;


public class JobProcessorApplication {
    private static final Logger LOG = Logger.getLogger(JobProcessorApplication.class.getName());
    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {
        try {
            LOG.info("Job started----");
            JobProcessorApplication application = new JobProcessorApplication();
            LOG.info("Adding secrets----");
            String clientId = new String(Files.readAllBytes(Paths.get("/mnt/secrets/ccpay/gateway-s2s-client-id")));
            String clientSecret = new String(Files.readAllBytes(Paths.get("/mnt/secrets/ccpay/gateway-s2s-client-secret")));
            LOG.info("Secrets added----");
            S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
            String s2sURL = System.getenv("S2S_URL");
            String paymentServerURL = System.getenv("PAYMENT_SERVER_URL");
            String reportName = System.getenv("REPORT_NAME");
            String s2sToken = s2STokenGeneration.generateOTP(clientSecret, s2sURL, clientId);
            LOG.info("s2sToken is generated");
            application.getJobProcessor(paymentServerURL, reportName, s2sToken);
        }
        catch(Exception ex)
        {
            LOG.log(Level.SEVERE,"Application crashed with error message:-----", ex);
        }

    }

    public void getJobProcessor(String baseURL, String jobType, String s2sToken) {
        LOG.info("baseURL--------"+baseURL+
                "jobType"+jobType);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(s2sToken,baseURL);
        LOG.info("Job completed successfully----");
    }

}
