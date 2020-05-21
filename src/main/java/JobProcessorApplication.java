import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.hmcts.payment.processors.EnvironmentVariableRetriever;
import uk.gov.hmcts.payment.processors.JobProcessorConfiguration;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;

import uk.gov.hmcts.payment.processors.S2SHelper;
import uk.gov.hmcts.payment.processors.VolumeReader;

public class JobProcessorApplication {
    private static final Logger LOG = LoggerFactory.getLogger(JobProcessorApplication.class.getName());
    public static void main(String[] args) {
        try {
            EnvironmentVariableRetriever envVarRetriever = new EnvironmentVariableRetriever();
            String mountPath = envVarRetriever
                    .get("VOLUME_PATH", "/mnt/secrets/ccpay/");

            JobProcessorConfiguration configuration = new JobProcessorConfiguration(envVarRetriever, new VolumeReader(mountPath));
            
            run(configuration);
        } catch(Exception ex) {
            LOG.error("Job failed", ex);
        }
    }

    public static void run(JobProcessorConfiguration configuration) {
        String s2sToken = new S2SHelper(configuration).generateToken();
        LOG.info("S2S Token generated");

        String reportName = configuration.getReportName();
        String payUrl = configuration.getPayUrl();

        LOG.info("Job {} started, Url: {}", reportName, payUrl);
        new JobProcessorFactory().getJobType(reportName).process(s2sToken, payUrl);
        LOG.info("Job completed successfully");
    }


}
