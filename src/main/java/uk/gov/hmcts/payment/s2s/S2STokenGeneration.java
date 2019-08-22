package uk.gov.hmcts.payment.s2s;

import io.restassured.RestAssured;
import org.jboss.aerogear.security.otp.Totp;
import org.json.JSONObject;


public class S2STokenGeneration {
    private Totp totp;
    
    public String generateOTP(String clientSecret, String authProviderServiceURL, String s2sClientId) throws Exception {

        totp = new Totp(clientSecret);
        String otp = totp.now();

        System.out.println("authProviderServiceURL---"+authProviderServiceURL+
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
