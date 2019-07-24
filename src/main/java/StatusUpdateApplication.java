import processors.JobProcessor;
import processors.JobProcessorFactory;

public class StatusUpdateApplication {

    private JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    public static void main(String args[])
    {
        StatusUpdateApplication application = new StatusUpdateApplication();
        application.statusUpdate(args[0],args[1],args[2]);

    }

    public void statusUpdate(String serviceToken, String baseURL, String jobType) {
        System.out.println("Values--------"+serviceToken+baseURL);
        JobProcessor jobProcessor =  jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(serviceToken,baseURL);
    }

}
