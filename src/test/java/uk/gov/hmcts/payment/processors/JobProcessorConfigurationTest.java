package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class JobProcessorConfigurationTest {
    
    @Test
    public void testNoConfig() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, JobProcessorConfiguration::new);
        assertThat(nullPointerException.getMessage(), is("S2S_URL environment variable is required"));
    }

}
