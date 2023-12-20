package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.response.order.OrderDataResponse;
import objects.response.order.OrderResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import services.BaseRestService;
import services.OrderService;

public class OrderSteps extends BaseRestService {
    OrderService orderService = new OrderService();
    @Given("I access to Order")
    public void iAccessToOrder() {
        scenarioContext.setContext("OrderResponse", orderService.getOrder424());
    }

    @Then("The order info: order id: {}, first name and last name: {} {}, status: {}, productId: {}, sku: {}")
    public void theOrderInfoOrderIdFirstNameAndLastNameStatusProductIdSku(Integer orderId, String firstName,
                                                                          String lastName,
                                                                          String status, Integer productId,
                                                                          String sku) {
        SoftAssert softAssert = new SoftAssert();
        OrderDataResponse orderDataResponse = scenarioContext.getContext("OrderResponse");
        softAssert.assertEquals(orderDataResponse.getOrderResponse().getOrderId(), orderId);
        softAssert.assertEquals(orderDataResponse.getOrderResponse().getCustomer().getFirstName(), firstName);
        softAssert.assertEquals(orderDataResponse.getOrderResponse().getCustomer().getLastName(), lastName);
        softAssert.assertEquals(orderDataResponse.getOrderResponse().getCustomer().getCustomerStatus(), status);
        softAssert.assertEquals(Integer.valueOf(orderDataResponse.getOrderResponse().getOrderDetail().get(0).getId()), productId);
        softAssert.assertEquals(orderDataResponse.getOrderResponse().getOrderDetail().get(0).getSku(), sku);
        softAssert.assertAll();

    }
}
