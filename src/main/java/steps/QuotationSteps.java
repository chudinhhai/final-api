package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.response.quotation.QuotationListResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import services.BaseRestService;
import services.QuotationService;

public class QuotationSteps extends BaseRestService {
    QuotationService quotationService = new QuotationService();
    @Given("I access to Quotation List")
    public void iAccessToQuotationList() {
        scenarioContext.setContext("QuotationListResponse", quotationService.getListOfQuotation());
    }

    @Then("The warehouse will be extracted")
    public void theWarehouseWillBeExtracted() {
        QuotationListResponse list = scenarioContext.getContext("QuotationListResponse");
        System.out.println(list.getQuotationList());
    }

    @And("Warehouse Name {string}, {string} is displayed")
    public void warehouseNameIsDisplayed(String name1, String name2) {
        Assert.assertTrue(quotationService.verifyWareHouseKuwaitNLabadi(name1, name2));
    }

    @And("{int} supplier is displayed in ASC")
    public void supplierIsDisplayedInASC(int total) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(quotationService.getNumberOfSupplier(), total);
        System.out.println(quotationService.getNumberOfSupplier());
        softAssert.assertTrue(quotationService.verifyASCOfSupplierLIst());
        softAssert.assertAll();

    }
}
