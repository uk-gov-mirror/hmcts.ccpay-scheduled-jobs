package uk.gov.hmcts.payment.processor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.hmcts.payment.*;


@RunWith(MockitoJUnitRunner.class)
public class JobProcessorFactoryTest {

    private JobProcessorFactory jobProcessorFactory;

    @Mock
    private PbaCsvReportProcessor pbaCsvReportProcessor;

    @Mock
    private CardCsvReportProcessor cardCsvReportProcessor;

    @Mock
    private StatusUpdateProcessor statusUpdateProcessor;

    @Mock
    private BarCsvReportProcessor barCsvReportProcessor;

    @Mock
    private PbaFinremWeeklyCsvReportProcessor pbaFinremWeeklyCsvReportProcessor;

    @Before
    public void setUp(){

        jobProcessorFactory = new JobProcessorFactory();
    }
    @Test
    public void shouldReturnNullWhenJobTypeIsNull() {
        jobProcessorFactory.getJobType(null);
    }

    @Test
    public void shouldReturnStatusUpdateProcessor() {
        jobProcessorFactory.getJobType("status-update");
        Assert.assertNotNull(statusUpdateProcessor);
    }

    @Test
    public void shouldReturnBarCsvReportProcessor() {
        jobProcessorFactory.getJobType("bar-csv-report");
        Assert.assertNotNull(barCsvReportProcessor);
    }

    @Test
    public void shouldReturnCardCsvReportProcessor() {
        jobProcessorFactory.getJobType("card-csv-report");
        Assert.assertNotNull(cardCsvReportProcessor);
    }

    @Test
    public void shouldReturnPbaCsvReportProcessor() {
        jobProcessorFactory.getJobType("pba-csv-report");
        Assert.assertNotNull(pbaCsvReportProcessor);
    }

    @Test
    public void shouldReturnPbaFinremWeeklyCsvReportProcessor() {
        jobProcessorFactory.getJobType("pba-finrem-weekly-csv-report");
        Assert.assertNotNull(pbaFinremWeeklyCsvReportProcessor);

    }

    @Test
    public void shouldReturnNullWhenJobTypeIsEmpty() {
        jobProcessorFactory.getJobType("");
    }

}
