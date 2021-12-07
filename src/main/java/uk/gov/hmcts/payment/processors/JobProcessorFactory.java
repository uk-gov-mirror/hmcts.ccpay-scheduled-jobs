package uk.gov.hmcts.payment.processors;

public class JobProcessorFactory {

    public JobProcessor getJobType(String jobType) {
        if (jobType == null) {
            return null;
        }
        if(jobType.equalsIgnoreCase("status-update")){
            return new StatusUpdateProcessor();

        }
        if(jobType.equalsIgnoreCase("dead-letter-queue-process")){
            return new DeadLetterQueueProcessor();

        }
        if(jobType.equalsIgnoreCase("bar-csv-report")){
            return new BarCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("card-csv-report")){
            return new CardCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("pba-csv-report")){
            return new PbaCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("pba-finrem-weekly-csv-report")){
            return new PbaFinremWeeklyCsvReportProcessor();

        }
        if(jobType.equalsIgnoreCase("refund-approved-update-process")){
            return new RefundApprovedUpdateProcessor();

        }
        if(jobType.equalsIgnoreCase("refund-notification-update-process")){
            return new RefundNotificationUpdateProcessor();

        }
        return null;
    }
}
