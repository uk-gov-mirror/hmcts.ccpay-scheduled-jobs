import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.hmcts.payment.JobProcessorRunner;
import uk.gov.hmcts.payment.processors.EnvironmentVariableRetriever;
import uk.gov.hmcts.payment.processors.JobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.VolumeReader;

public class JobProcessorApplication {
    private static final Logger LOG = LoggerFactory.getLogger(JobProcessorApplication.class.getName());
    public static void main(String[] args) {
        try {
            EnvironmentVariableRetriever envVarRetriever = new EnvironmentVariableRetriever();
            String mountPath = envVarRetriever
                    .get("VOLUME_PATH", "/mnt/secrets/ccpay/");

            JobProcessorConfiguration configuration = new JobProcessorConfiguration(envVarRetriever, new VolumeReader(mountPath));
            
            JobProcessorRunner.run(configuration);
        } catch(Exception ex) {
            LOG.error("Job failed", ex);
        }
    }
}
