import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;



public class JobProcessorApplication {

    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {
        try {
            System.out.println("Job started----");
            JobProcessorApplication application = new JobProcessorApplication();
            S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
            String s2sToken = s2STokenGeneration.generateOTP(args[0],args[1],args[2]);
            System.out.println("s2sToken-----"+s2sToken);
            application.getJobProcessor(args[3], args[4],s2sToken);
        }
        catch(Exception ex)
        {
            System.out.println("Application crashed with error message:-----"+ex);
        }

    }

    public void getJobProcessor(String baseURL, String jobType, String s2sToken) {
        System.out.println("baseURL--------"+baseURL+
                "jobType"+jobType+
                "s2sToken"+s2sToken);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(s2sToken,baseURL);
        System.out.println("Job completed successfully----");
    }

}
