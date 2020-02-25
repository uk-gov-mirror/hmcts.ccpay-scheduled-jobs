package uk.gov.hmcts.payment.s2s;

import io.restassured.RestAssured;
import org.jboss.aerogear.security.otp.Totp;
import org.json.JSONObject;

import java.util.logging.Logger;


public class S2STokenGeneration {
    private static final Logger LOG = Logger.getLogger(S2STokenGeneration.class.getName());
    private Totp totp;
    
    public String generateOTP(String clientSecret, String authProviderServiceURL, String s2sClientId){

        totp = new Totp(clientSecret);
        String otp = totp.now();

        LOG.info("authProviderServiceURL---"+authProviderServiceURL+
                "otp---"+otp);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("microservice", s2sClientId);
        jsonObject.put("oneTimePassword", otp);
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .post(authProviderServiceURL + "/lease")
                .getBody()
                .asString();

    }
}
