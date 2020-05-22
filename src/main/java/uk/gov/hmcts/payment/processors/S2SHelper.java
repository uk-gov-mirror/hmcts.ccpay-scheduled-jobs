package uk.gov.hmcts.payment.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.codec.StringDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import uk.gov.hmcts.reform.authorisation.ServiceAuthorisationApi;
import uk.gov.hmcts.reform.authorisation.generators.AuthTokenGeneratorFactory;

public class S2SHelper {
    
    private final JobProcessorConfiguration configuration;

    public S2SHelper(JobProcessorConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Generates the JWT from the s2s service
     * Note: Bearer is already prefixed when returned, no need to add it yourself
     * 
     * @return a string in the form 'Bearer my-jwt'
     */
    public String generateToken() {
            HttpMessageConverter<?> jsonConverter = new MappingJackson2HttpMessageConverter(new ObjectMapper());
            ObjectFactory<HttpMessageConverters> converter = () -> new HttpMessageConverters(jsonConverter);

            ServiceAuthorisationApi serviceAuthorisationApi = Feign.builder()
                    .contract(new SpringMvcContract())
                    .encoder(new SpringEncoder(converter))
                    .decoder(new StringDecoder())
                    .target(ServiceAuthorisationApi.class, configuration.getS2sUrl());

            return AuthTokenGeneratorFactory
                    .createDefaultGenerator(configuration.getS2sSecret(), configuration.getS2sMicroserviceId(), serviceAuthorisationApi)
                    .generate();
    }
}
