package services;

import config.ScenarioContext;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class BaseRestService {
    private String host;
    private String protocol = "https";
    private int port = 443;
    private Map<String, String> cookies = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    protected final ScenarioContext scenarioContext;

    public BaseRestService() {
        this.scenarioContext = ScenarioContext.getInstance();
    }

    public String getServiceUrl(){
        return String.format("%s://%s", this.protocol, this.host);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public Response dispatchServiceRequest(RequestSpecification request, Method method){
        switch (method){
            case POST:
                return request.post().thenReturn();
            case PUT:
                return request.put().thenReturn();
            case GET:
                return request.get().thenReturn();
            case DELETE:
                return request.delete().thenReturn();
            case HEAD:
                return request.head().thenReturn();
            case OPTIONS:
                return request.options().thenReturn();
            case PATCH:
                return request.patch().thenReturn();
            default:
                throw new RuntimeException(
                        String.format("Not Support Request Method %s", method.name())
                );
        }
    }
    public RequestSpecification getDefaultRequestBuilder(String apiPath){
        headers.put("Accept","application/json, text/plain, */*");
        headers.put("Content-type", "application/json");
        headers.put("Connection", "keep-alvie");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/119.0.0.0 Safari/537.36");
        headers.put("Accept-Langague", "en-GB,en;q=0.8,vi;q=0.7,fr-FR;q=0.6,fr;q=0.5,en-US;q=0.4");
        return RestAssured.given()
                .contentType("application/json")
                .baseUri(getServiceUrl())
                .basePath(apiPath)
                .cookies(cookies)
                .headers(headers);
    }
}
