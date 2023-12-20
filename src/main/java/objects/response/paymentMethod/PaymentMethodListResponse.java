package objects.response.paymentMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethodListResponse {
    public PaymentMethodListResponse() {
    }

    @JsonProperty("data")
    private List<PaymentMethodResponse> paymentMethodResponseList;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty
    public List<PaymentMethodResponse> getPaymentMethodList() {
        return paymentMethodResponseList;
    }

    public void setPaymentMethodList(List<PaymentMethodResponse> paymentMethodResponseList) {
        this.paymentMethodResponseList = paymentMethodResponseList;
    }

    public List<PaymentMethodResponse> getPaymentMethodResponseList() {
        return paymentMethodResponseList;
    }

    public void setPaymentMethodResponseList(List<PaymentMethodResponse> paymentMethodResponseList) {
        this.paymentMethodResponseList = paymentMethodResponseList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
