import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import uk.gov.hmcts.payment.s2s.S2STokenGeneration;



public class JobProcessorApplication {
    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {
        try {
            System.out.println("Job started----");
            System.out.println("Slot----"+args[0]);
            JobProcessorApplication application = new JobProcessorApplication();
            if(args[0].trim().equalsIgnoreCase("PRODUCTION")) {
                System.out.println("App slot is supported----");
                S2STokenGeneration s2STokenGeneration = new S2STokenGeneration();
                String s2sToken = s2STokenGeneration.generateOTP(args[1], args[2], args[3]);
                System.out.println("s2sToken is generated");
                application.getJobProcessor(args[4], args[5], s2sToken);
            }
            else
            {
                throw new IllegalArgumentException("Unsupported app slot to run this application"+args[0]);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Application crashed with error message:-----"+ex);
        }

    }

    public void getJobProcessor(String baseURL, String jobType, String s2sToken) {
        System.out.println("baseURL--------"+baseURL+
                "jobType"+jobType);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(s2sToken,baseURL);
        System.out.println("Job completed successfully----");
    }

}
