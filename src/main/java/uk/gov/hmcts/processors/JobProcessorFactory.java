package uk.gov.hmcts.processors;

public class JobProcessorFactory {

    public JobProcessor getJobType(String jobType) {
        if (jobType == null) {
            return null;
        }
        if(jobType.equalsIgnoreCase("status-update")){
            return new StatusUpdateProcessor();

        }
        return null;
    }
}
