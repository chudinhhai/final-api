package objects.response.paymentMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethodResponse {
    public PaymentMethodResponse() {
    }

    @JsonProperty("payment_method_name")
    private String name;
    @JsonProperty("payment_setting")
    private List<PaymentSettingResponse> settingList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentSettingResponse> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<PaymentSettingResponse> settingList) {
        this.settingList = settingList;
    }
}
