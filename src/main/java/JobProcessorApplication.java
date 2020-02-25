import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;

import java.util.logging.Logger;


public class JobProcessorApplication {
    private static final Logger LOG = Logger.getLogger(JobProcessorApplication.class.getName());
    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {
        try {
            JobProcessorApplication application = new JobProcessorApplication();
            if(args[0].trim().equalsIgnoreCase("PRODUCTION")) {
                S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
                String s2sToken = s2STokenGeneration.generateOTP(args[1], args[2], args[3]);
                application.getJobProcessor(args[4], args[5], s2sToken);
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
