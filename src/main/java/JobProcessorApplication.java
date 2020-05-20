import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.codec.StringDecoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import uk.gov.hmcts.payment.processors.JobProcessor;
import uk.gov.hmcts.payment.processors.JobProcessorFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGenerator;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGeneratorFactory;

import static java.util.Objects.requireNonNull;


public class JobProcessorApplication {
    private static final Logger LOG = Logger.getLogger(JobProcessorApplication.class.getName());
    private final JobProcessorFactory jobProcessorFactory = new JobProcessorFactory();
    
    private static final String MOUNT_PATH;
    
    static {
        String tempMountPath = System.getenv("VOLUME_PATH");
        if (tempMountPath == null) {
            tempMountPath = "/mnt/secrets/ccpay/";
        }
        MOUNT_PATH = tempMountPath;
    }
    
    public static void main(String[] args) {
        try {
            String s2sURL = System.getenv("S2S_URL");
            requireNonNull(s2sURL);

            String paymentServerURL = System.getenv("PAYMENT_SERVER_URL");
            requireNonNull(paymentServerURL);
            
            String reportName = System.getenv("REPORT_NAME");
            requireNonNull(reportName);
                    
            LOG.info("Job started----");
            JobProcessorApplication application = new JobProcessorApplication();
            LOG.info("Adding secrets----");
            
            String clientId = getFromVolume("gateway-s2s-client-id");
            String clientSecret = getFromVolume("gateway-s2s-client-secret");
            LOG.info("Secrets added----");

            AuthTokenGenerator authTokenGenerator = getAuthTokenGenerator(s2sURL, clientId, clientSecret);

            String s2sToken = authTokenGenerator.generate();
            LOG.info("S2S Token generated");
            application.getJobProcessor(paymentServerURL, reportName, s2sToken);
        } catch(Exception ex) {
            LOG.log(Level.SEVERE,"Application crashed with error message:-----", ex);
        }

    }

    private static AuthTokenGenerator getAuthTokenGenerator(String s2sURL, String clientId, String clientSecret) {
        HttpMessageConverter<?> jsonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
        ObjectFactory<HttpMessageConverters> converter = () -> new HttpMessageConverters(jsonConverter);

        ServiceAuthorisationApi serviceAuthorisationApi = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(converter))
                .decoder(new StringDecoder())
                .target(ServiceAuthorisationApi.class, s2sURL);

        return AuthTokenGeneratorFactory
                .createDefaultGenerator(clientSecret, clientId, serviceAuthorisationApi);
    }

    private static String getFromVolume(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(MOUNT_PATH, fileName)), StandardCharsets.UTF_8).trim();
    }

    public void getJobProcessor(String baseURL, String jobType, String s2sToken) {
        LOG.info("baseURL: " + baseURL + "jobType: " + jobType);
        JobProcessor jobProcessor = jobProcessorFactory.getJobType(jobType);
        jobProcessor.process(s2sToken,baseURL);
        LOG.info("Job completed successfully----");
    }

}
