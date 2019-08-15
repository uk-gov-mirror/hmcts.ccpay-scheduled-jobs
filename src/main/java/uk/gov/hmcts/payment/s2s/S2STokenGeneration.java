package uk.gov.hmcts.payment.s2s;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jboss.aerogear.security.otp.Totp;
import org.json.JSONObject;


public class S2STokenGeneration {
    private Totp totp;
    
    public String generateOTP(String clientSecret, String authProviderServiceURL, String s2sClientId) throws Exception {

        totp = new Totp(clientSecret);
        String otp = totp.now();

        System.out.println("clientSecret---"+clientSecret+
                "authProviderServiceURL---"+authProviderServiceURL+
                "s2sClientId---"+s2sClientId+
                "otp---"+otp);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("microservice", s2sClientId);
        jsonObject.put("oneTimePassword", otp);
        return "Bearer " + RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(jsonObject.toString())
                .post(authProviderServiceURL + "/lease")
                .getBody()
                .asString();

    }
}
