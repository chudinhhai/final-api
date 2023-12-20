package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.response.order.OrderListResponse;
import objects.response.quotation.QuotationListResponse;
import objects.response.quotation.QuotationResponse;
import objects.response.quotation.SupplierResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuotationService extends BaseRestService{
    public QuotationService() {
        this.setProtocol("https");
        this.setHost("rawal-admin.themes-coder.net");
    }
    public QuotationListResponse getListOfQuotation(){
        QuotationListResponse quotationListResponse;
        getHeaders().put("Authorization", "Bearer " + scenarioContext.getContext("TOKEN"));
        RequestSpecification spec = this.getDefaultRequestBuilder("/api/admin/quotation");
        spec.param("limit", "10");
        spec.param("searchParameter", "");
        spec.param("getPaymentMethodSetting", "1");
        spec.param("sortBy", "id");
        spec.param("sortType", "ASC");
        spec.param("getSupplier", "1");
        spec.param("getCustomer", "1");
        spec.param("getWarehouse", "1");
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            quotationListResponse = objectMapper.readValue(response.body().asString(), QuotationListResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return quotationListResponse;
    }
    public boolean verifyWareHouseKuwaitNLabadi(String name1, String name2){
        QuotationListResponse list = scenarioContext.getContext("QuotationListResponse");
        List<String> warehouseName = new ArrayList<>();
        for (QuotationResponse response : list.getQuotationList()){
            warehouseName.add(response.getWarehouse().getWarehouseName());
        }
        return warehouseName.contains(name1) && warehouseName.contains(name2);
    }
    public int getNumberOfSupplier(){
        QuotationListResponse list = scenarioContext.getContext("QuotationListResponse");
        return list.getQuotationList().size();
    }
    public boolean verifyASCOfSupplierLIst(){
        QuotationListResponse list = scenarioContext.getContext("QuotationListResponse");
        List<SupplierResponse> supplierList = list.getQuotationList().stream().map(QuotationResponse::getSupplier).collect(Collectors.toList());
        List<Integer> lsID = supplierList.stream().map(SupplierResponse::getId).collect(Collectors.toList());
//        System.out.println(lsID);
        return checkASC(lsID);
    }
    public static boolean checkASC(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
