package uk.gov.hmcts.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class JobProcessorFactoryTest {

    @Mock
    JobProcessorFactory jobProcessorFactory;
    @Mock
    StatusUpdateProcessor statusUpdateProcessor;

    @Mock
    private  JobProcessor jobProcessor;

    @Before
    public void setUp(){

    }
    @Test
    public void shouldReturnNullWhenJobTypeIsNull() {
        Mockito.when(jobProcessorFactory.getJobType(anyString())).thenReturn(null);
        Assert.assertNull(jobProcessorFactory.getJobType(anyString()));
    }

    @Test
    public void shouldReturnJobTypeAsPerInput() {
        Mockito.when(jobProcessorFactory.getJobType(anyString())).thenReturn(statusUpdateProcessor);
        Assert.assertNotNull(statusUpdateProcessor);
    }
}
