package uk.gov.hmcts.payment.processors;

public class MockJobProcessorConfiguration extends JobProcessorConfiguration {

    private final String s2sUrl;
    private final String payUrl;

    public MockJobProcessorConfiguration(String s2sUrl, String payUrl) {
        super(new EnvironmentVariableRetriever() {
            @Override
            public String get(String environmentVariable) {
                return null;
            }

            @Override
            public String get(String environmentVariable, String defaultValue) {
                return null;
            }
        }, new VolumeReader(null) {
            @Override
            public String getFromVolume(String fileName) {
                return null;
            }
        } );
        this.s2sUrl = s2sUrl;
        this.payUrl = payUrl;
    }

    @Override
    public String getS2sUrl() {
        return this.s2sUrl;
    }

    @Override
    public String getS2sMicroserviceId() {
        return "api_gw";
    }

    @Override
    public String getS2sSecret() {
        // dummy secret:
        return "UIEAKQOHFQUYFLTJ";
    }

    @Override
    public String getPayUrl() {
        return this.payUrl;
    }

    @Override
    public String getReportName() {
        return "pba-csv-report";
    }
}
