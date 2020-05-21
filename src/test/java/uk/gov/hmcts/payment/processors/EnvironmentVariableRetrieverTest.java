package uk.gov.hmcts.payment.processors;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentVariableRetrieverTest {

    @Test
    void getWithDefault() {
        EnvironmentVariableRetriever retriever = new EnvironmentVariableRetriever();
        
        assertThat(retriever.get("ASDASDSADSA", "default"), is("default"));
    }

    @Test
    void getThrowsIfNotFound() {
        EnvironmentVariableRetriever retriever = new EnvironmentVariableRetriever();

        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> retriever.get("ASDASDSADSA"));
        assertThat(nullPointerException.getMessage(), is("ASDASDSADSA environment variable is required"));
    }
}
