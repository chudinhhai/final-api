package objects.response.quotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotationListResponse {
    public QuotationListResponse() {
    }
    @JsonProperty("data")
    private List<QuotationResponse> quotationList;

    public List<QuotationResponse> getQuotationList() {
        return quotationList;
    }

    public void setQuotationList(List<QuotationResponse> quotationList) {
        this.quotationList = quotationList;
    }

    @Override
    public String toString() {
        return "QuotationListResponse{" +
                "quotationList=" + quotationList +
                '}';
    }
}
