package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.response.order.OrderDataResponse;
import objects.response.order.OrderListResponse;
import objects.response.order.OrderResponse;
import objects.response.paymentMethod.PaymentMethodListResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService extends BaseRestService{
    public OrderService() {
        this.setProtocol("https");
        this.setHost("rawal-admin.themes-coder.net");
    }
    public OrderListResponse getListOfOrder(){
        OrderListResponse orderListResponse;
        getHeaders().put("Authorization", "Bearer " + scenarioContext.getContext("TOKEN"));
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/admin/order");
        spec.param("limit", "10");
        spec.param("searchParameter", "");
        spec.param("getPaymentMethodSetting", "1");
        spec.param("sortBy", "id");
        spec.param("sortType", "DESC");
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orderListResponse = objectMapper.readValue(response.body().asString(), OrderListResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return orderListResponse;
    }
    public int getNumberOfOrderFromPOS(){
        OrderListResponse list = scenarioContext.getContext("OrderListResponse");
        return list.getOrderList().stream().filter(location -> location.getSenderLocation().equals("pos")).collect(Collectors.toList()).size();
    }
    public boolean verifyDESCOfOrderList(){
        OrderListResponse list = scenarioContext.getContext("OrderListResponse");
        List<Integer> lsID = list.getOrderList().stream().map(OrderResponse::getOrderId).collect(Collectors.toList());
        return checkDESC(lsID);
    }
    public static boolean checkDESC(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public OrderDataResponse getOrder424(){
        OrderDataResponse orderDataResponse;
        getHeaders().put("Authorization", "Bearer " + scenarioContext.getContext("TOKEN"));
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/admin/order/424");
        spec.param("orderDetail", "1");
        spec.param("productDetail", "1");
        spec.param("currency", "1");
        spec.param("billing_country", "1");
        spec.param("billing_state", "1");
        spec.param("delivery_country", "1");
        spec.param("delivery_state", "1");
        spec.param("customer", "1");
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            orderDataResponse = objectMapper.readValue(response.body().asString(), OrderDataResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return orderDataResponse;
    }

    public OrderResponse verifyOrder(){
        return scenarioContext.getContext("OrderResponse");
    }

}
