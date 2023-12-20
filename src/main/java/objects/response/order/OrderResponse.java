package objects.response.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
    public OrderResponse() {
    }
    @JsonProperty("order_id")
    private Integer orderId;
    @JsonProperty("order_from")
    private String senderLocation;
    @JsonProperty("customer_id")
    private Customer customer;
    @JsonProperty("order_detail")
    private List<OrderDetail> orderDetail;
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getSenderLocation() {
        return senderLocation;
    }

    public void setSenderLocation(String senderLocation) {
        this.senderLocation = senderLocation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId=" + orderId +
                ", senderLocation='" + senderLocation + '\'' +
                ", customer=" + customer +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
