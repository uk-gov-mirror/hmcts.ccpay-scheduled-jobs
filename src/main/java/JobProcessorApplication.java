import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Logger;


public class JobProcessorApplication {
    private static final Logger LOG = Logger.getLogger(JobProcessorApplication.class.getName());
    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {

        try {
            LOG.info("Job started----");
            LOG.info("Slot----"+args[0]);
            JobProcessorApplication application = new JobProcessorApplication();
            String client_id = new String(Files.readAllBytes(Paths.get("mnt/secrets/s2s/gateway-s2s-client-id")));
            String client_secret = new String(Files.readAllBytes(Paths.get("mnt/secrets/s2s/gateway-s2s-client-secret")));
            if(args[0].trim().equalsIgnoreCase("PRODUCTION")) {
                LOG.info("App slot is supported----");
                S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
                String s2sToken = s2STokenGeneration.generateOTP(client_secret, args[1], client_id);
                LOG.info("s2sToken is generated");
                application.getJobProcessor(args[2], args[3], s2sToken);
            }
            else
            {
                throw new IllegalArgumentException("Unsupported app slot to run this application"+args[0]);
            }
        }
        catch(Exception ex)
        {
            LOG.info("Application crashed with error message:-----"+ex);
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
