package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.ja.前提;
import objects.response.paymentMethod.PaymentMethodListResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import services.PaymentMethodService;

import java.util.Optional;

public class PaymentMethodSteps extends BaseSteps{
    PaymentMethodService paymentMethodService = new PaymentMethodService();
    @Given("I access to Payment Methods")
    public void iAccessToPaymentMethods() {
        PaymentMethodListResponse paymentMethodListResponse = paymentMethodService.getListOfPaymentMethod();
        scenarioContext.setContext("PaymentMethodListResponse", paymentMethodListResponse);
        scenarioContext.setContext("PaymentStatus", paymentMethodService.getPaymentStatus());
        scenarioContext.setContext("PaymentMessage", paymentMethodService.getPaymentMessage());
        scenarioContext.setContext("PaymentStatusCode", paymentMethodService.getPaymentStatusCode());

    }

    @Then("The data has status {string}, message {string} and status code {string}")
    public void theDataHasStatusMessageAndStatusCode(String status, String message, String code) {
        Assert.assertEquals(scenarioContext.getContext("PaymentStatus"), status);
        Assert.assertEquals(scenarioContext.getContext("PaymentMessage"), message);
        Assert.assertEquals(scenarioContext.getContext("PaymentStatusCode"), code);
    }

    @And("The Payment method name are:")
    public void thePaymentMethodNameAre(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        for (String name : dataTable.asList()){
            softAssert.assertTrue(paymentMethodService.getPaymentMethodName().contains(name));
        }
        softAssert.assertAll();
    }


    @And("The Payment setting of Paypal method contains:")
    public void thePaymentSettingOfPaypalMethodContains(DataTable dataTable) {
        Assert.assertEquals(dataTable.asList(), paymentMethodService.getPaymentPaypalSetting());
    }
}
