package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.request.authenticate.LoginRequest;
import objects.response.authenticate.LoginResponse;
import objects.response.authenticate.VerifyTokenResponse;

public class LoginService extends BaseRestService{
    public LoginService(){
        this.setProtocol("https");
        this.setHost("rawal-admin.themes-coder.net");
    }
    public String getToken(LoginRequest loginRequest) throws JsonProcessingException {
        String token = "";
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/login")
                .body(loginRequest.convertObjectToJSONString());
        Response response = this.dispatchServiceRequest(spec, Method.POST);
        String resposneString = response.body().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        LoginResponse loginResponse = new LoginResponse();
        try {
            loginResponse = objectMapper.readValue(resposneString, LoginResponse.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        return loginResponse.getToken();
    }
    public boolean verifyToken(String token){
        boolean checkToken = false;
        getHeaders().put("Authorization", "Bearer " + scenarioContext.getContext("TOKEN"));
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/admin/token-validate")
                .body("{}");
        Response response = this.dispatchServiceRequest(spec, Method.POST);
        String responseString = response.body().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        VerifyTokenResponse verifyTokenResponse = new VerifyTokenResponse();
        try {
            verifyTokenResponse = objectMapper.readValue(responseString, VerifyTokenResponse.class);
        } catch (JsonProcessingException e) {

        }
        return verifyTokenResponse.getMessage().equals("Token is valid!");
    }
}
