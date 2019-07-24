import processors.JobProcessor;
import processors.JobProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JobProcessorApplication {

    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    private static final Logger LOG = LoggerFactory.getLogger(JobProcessorApplication.class);
    public static void main(String args[])
    {
        try {
            JobProcessorApplication application = new JobProcessorApplication();
            application.statusUpdate(args[0], args[1], args[2]);
        }
        catch(Exception ex)
        {
            LOG.error("Application crashed with error message: ", ex);
        }

    }

    public void statusUpdate(String serviceToken, String baseURL, String jobType) {
        LOG.error("Input Values--------"+serviceToken+baseURL+jobType);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(serviceToken,baseURL);
    }

}
