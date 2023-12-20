package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.response.order.OrderListResponse;
import org.testng.Assert;
import services.BaseRestService;
import services.OrderService;

public class OrderListSteps extends BaseRestService {
    OrderService orderService = new OrderService();
    @Given("I access to Order List")
    public void iAccessToOrderList() {
        OrderListResponse list = orderService.getListOfOrder();
        scenarioContext.setContext("OrderListResponse", list);
    }

    @Then("The total order from POS is {int} and the order id is sorted by DESC")
    public void theTotalOrderFromPOSIsAndTheOrderIdIsSortedByDESC(int number) {
        Assert.assertEquals(orderService.getNumberOfOrderFromPOS(), number);
        Assert.assertTrue(orderService.verifyDESCOfOrderList());
    }
}
