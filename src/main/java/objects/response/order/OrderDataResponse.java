package objects.response.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDataResponse {
    public OrderDataResponse() {

    }
    @JsonProperty("data")
    private OrderResponse orderResponse;

    public OrderResponse getOrderResponse() {
        return orderResponse;
    }

    public void setOrderResponse(OrderResponse orderResponse) {
        this.orderResponse = orderResponse;
    }

    @Override
    public String toString() {
        return "OrderDataResponse{" +
                "orderResponse=" + orderResponse +
                '}';
    }
}
