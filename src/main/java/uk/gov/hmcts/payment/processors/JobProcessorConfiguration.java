package uk.gov.hmcts.payment.processors;

import static java.util.Objects.requireNonNull;

public class JobProcessorConfiguration {

    private String s2sUrl;
    private String s2sMicroserviceId;
    private String s2sSecret;
    private String payUrl;
    private String reportName;

    public JobProcessorConfiguration() {
        this(new EnvironmentVariableRetriever());
    }

    public JobProcessorConfiguration(EnvironmentVariableRetriever envVarRetriever) {
        this.s2sUrl = envVarRetriever.get("S2S_URL");
        this.payUrl = envVarRetriever.get("PAYMENT_SERVER_URL");
        requireNonNull(payUrl, "PAYMENT_SERVER_URL environment variable is required");

        this.reportName = envVarRetriever.get("REPORT_NAME");
        requireNonNull(reportName, "REPORT_NAME environment variable is required");

        String mountPath = envVarRetriever
                .get("VOLUME_PATH", "/mnt/secrets/ccpay/");
        
        VolumeReader volumeReader = new VolumeReader(mountPath);
        this.s2sMicroserviceId = volumeReader.getFromVolume("gateway-s2s-client-id");
        this.s2sSecret = volumeReader.getFromVolume("gateway-s2s-client-secret");
    }

    public String getS2sUrl() {
        return s2sUrl;
    }

    public String getS2sMicroserviceId() {
        return s2sMicroserviceId;
    }

    public String getS2sSecret() {
        return s2sSecret;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public String getReportName() {
        return reportName;
    }
}
