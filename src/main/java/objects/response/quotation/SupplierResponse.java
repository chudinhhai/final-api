package objects.response.quotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierResponse {
    public SupplierResponse() {
    }
    @JsonProperty("supplier_id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SupplierResponse{" +
                "id=" + id +
                '}';
    }
}
