package uk.gov.hmcts.payment.processors;

import static java.util.Objects.requireNonNull;

public class JobProcessorConfiguration {

    private String s2sUrl;
    private String s2sMicroserviceId;
    private String s2sSecret;
    private String payUrl;
    private String reportName;
    
    public JobProcessorConfiguration() {
        this.s2sUrl = System.getenv("S2S_URL");
        requireNonNull(s2sUrl);

        this.payUrl = System.getenv("PAYMENT_SERVER_URL");
        requireNonNull(payUrl);

        this.reportName = System.getenv("REPORT_NAME");
        requireNonNull(reportName);

        String mountPath = System.getenv("VOLUME_PATH");
        if (mountPath == null) {
            mountPath = "/mnt/secrets/ccpay/";
        }
        
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
