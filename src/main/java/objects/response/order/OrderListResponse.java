package objects.response.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderListResponse {
    public OrderListResponse() {
    }
    @JsonProperty("data")
    private List<OrderResponse> orderResponseList;

    public List<OrderResponse> getOrderList() {
        return orderResponseList;
    }

    public void setOrderList(List<OrderResponse> orderResponseList) {
        this.orderResponseList = orderResponseList;
    }

    @Override
    public String toString() {
        return "OrderListResponse{" +
                "orderResponseList=" + orderResponseList +
                '}';
    }
}
