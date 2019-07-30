package uk.gov.hmcts.payment.s2s;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jboss.aerogear.security.otp.Totp;


public class S2STokenGeneration {
    private Totp totp;
    private S2sTokenService s2sTokenService = new S2sTokenService();
    
    public String generateOTP(String clientSecret, String authProviderServiceURL, String s2sClientId) throws Exception {

        totp = new Totp(clientSecret);
        String otp = totp.now();

        System.out.println("Values---"+clientSecret+authProviderServiceURL+s2sClientId+otp);
          Response response = RestAssured.given().relaxedHTTPSValidation()
                .baseUri(authProviderServiceURL)
                .contentType(ContentType.JSON)
                .param("microservice", s2sClientId)
                .param("oneTimePassword", otp)
                .post("/lease");
        System.out.println("Response Values---"+response.getBody().asString());
        System.out.println("s2sTokenService---"+s2sTokenService);
        //System.out.println("Values---"+s2sTokenService.getS2sToken(s2sClientId,s2s));
        //String s2sToken = s2sTokenService.getS2sToken(s2sClientId,s2s);

        //return s2sToken;
        return response.getBody().asString();

    }
}
