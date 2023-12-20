package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.response.paymentMethod.PaymentMethodListResponse;
import objects.response.paymentMethod.PaymentMethodResponse;
import objects.response.paymentMethod.PaymentSettingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentMethodService extends BaseRestService{
    public PaymentMethodService() {
        this.setProtocol("https");
        this.setHost("rawal-admin.themes-coder.net");
    }
    public PaymentMethodListResponse getListOfPaymentMethod(){
        PaymentMethodListResponse paymentMethodListResponse;
        getHeaders().put("Authorization", "Bearer " + scenarioContext.getContext("TOKEN"));
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/admin/payment_method");
        spec.param("limit", "10");
        spec.param("searchParameter", "");
        spec.param("getPaymentMethodSetting", "1");
        spec.param("sortBy", "id");
        spec.param("sortType", "ASC");
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            paymentMethodListResponse = objectMapper.readValue(response.body().asString(), PaymentMethodListResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return paymentMethodListResponse;
    }
    public List<String> getPaymentMethodName(){
        PaymentMethodListResponse list = scenarioContext.getContext("PaymentMethodListResponse");
        List<String> methodNameList = new ArrayList<>();
        for (PaymentMethodResponse paymentMethod : list.getPaymentMethodList()){
            methodNameList.add(paymentMethod.getName());
        }
        return methodNameList;
    }
    public List<String> getPaymentPaypalSetting(){
        PaymentMethodListResponse list = scenarioContext.getContext("PaymentMethodListResponse");
        Optional<PaymentMethodResponse> payment =
                list.getPaymentMethodList().stream().filter(paymentMethodResponse -> paymentMethodResponse.getName().equals("paypal")).findFirst();
        return payment.get().getSettingList().stream().map(PaymentSettingResponse::getKey).collect(Collectors.toList());
    }
    public String getPaymentStatus(){
        PaymentMethodListResponse list = scenarioContext.getContext("PaymentMethodListResponse");
        return list.getStatus();
    }
    public String getPaymentMessage(){
        PaymentMethodListResponse list = scenarioContext.getContext("PaymentMethodListResponse");
        return list.getMessage();
    }
    public String getPaymentStatusCode(){
        PaymentMethodListResponse list = scenarioContext.getContext("PaymentMethodListResponse");
        return list.getStatusCode();
    }
}
