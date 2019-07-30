import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;



public class JobProcessorApplication {

    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    private static final Logger LOG = LoggerFactory.getLogger(JobProcessorApplication.class);
    public static void main(String args[])
    {
        try {
            System.out.println("Inside main method");
            JobProcessorApplication application = new JobProcessorApplication();
            S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
            String s2sToken = s2STokenGeneration.generateOTP(args[0],args[1],args[2]);
            System.out.println("s2sToken"+s2sToken);
            application.getJobProcessor(args[3], args[4],s2sToken);
        }
        catch(Exception ex)
        {
            LOG.error("Application crashed with error message: ", ex);
        }

    }

    public void getJobProcessor(String baseURL, String jobType, String s2sToken) {
        LOG.error("Input Values--------"+s2sToken+baseURL+jobType);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(s2sToken,baseURL);
    }

}
