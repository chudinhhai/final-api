package objects.response.quotation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotationResponse {
    public QuotationResponse() {
    }
    @JsonProperty("supplier")
    private SupplierResponse supplier;
    @JsonProperty("warehouse")
    private WarehouseResponse warehouse;

    public SupplierResponse getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponse supplier) {
        this.supplier = supplier;
    }

    public WarehouseResponse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseResponse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "QuotationResponse{" +
                "supplier=" + supplier +
                ", warehouse=" + warehouse +
                '}';
    }
}
