Feature:Verify Rawal Page
  Background:
    When I login to Rawal by using email: "owner@email.com" and password: "123"
  @TC_01
  Scenario: Verify Payment Method
    Given I access to Payment Methods
    Then The data has status "Success", message "Data Get Successfully!" and status code "200"
    And The Payment method name are:
      | paypal        |
      | stripe        |
      | banktransfer  |
      | cod           |
      | braintree     |
      | authorize_net |
      | mollie        |
      | sagepay       |
      | razorpay      |
    And The Payment setting of Paypal method contains:
      | PAYPAL_API_SIGNATURE |
      | PAYPAL_API_PASSWORD  |
      | PAYPAL_API_USERNAME  |

  @TC_02
  Scenario: Verify Order List
    Given I access to Order List
    Then The total order from POS is 10 and the order id is sorted by DESC

  @TC_03
  Scenario Outline: Verify Order
    Given I access to Order
    Then The order info: order id: <OrderId>, first name and last name: <firstName> <lastName>, status: <status>, productId: <productId>, sku: <productSKU>
    Examples:
      | OrderId | firstName | lastName  | status | productId | productSKU |
      | 424     | Guillermo | Gutierrez | 1      | 412       | grapes-1   |

  @TC_04
  Scenario: Verify Quotation List
    Given I access to Quotation List
    Then The warehouse will be extracted
    And Warehouse Name "Labadi", "kuwait" is displayed
    And 10 supplier is displayed in ASC