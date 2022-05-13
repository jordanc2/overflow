# [Team Overflow] Integration Test Plan

## Instructions

*Create a copy of this template for your team and save it to the same folder.*

*Fill out the test plan following the instructions provided, replacing/removing
the text in italics (including this section!) as you go. You will review this
document with the team you’re paired with for the unit.*

## Purpose

This captures the test plan for your project, including:

* testing of endpoints through automated integration tests
* testing of front-end through manual browser-based testing

Use the endpoint test plan to create your automated integration tests. Use the
front-end test plan to ensure that your front-end is working. Manual front-end
tests are more expensive, so aim to have fewer of them, and you may want to run
these at key milestones in your project (and certainly before presentation
day!).

## Product Background

Our project is a product inventory management system.  A user will be able to perform full CRUD operations
on a product of their choice. They will also have the ability to add and remove that product to and from their
inventory.
<br>
[Team Overflow Design Document](design_document.md)

### Use Cases

U1. As a [Overflow] user, be able to log into the management system

U2. As a [Overflow] user, be able to create, update and delete products

U3. As a [Overflow] user, be able to retrieve a list of inventory

# Automated Integration Test Plan

*Organize your tests by use cases from your design document. Provide the entire
“Use Case:...” section below for each Use Case you will implement in the
project. If you have more than one test case for a given use case, repeat the
“Test Case” section below for each test case in that use case.*

*The goal should be that any member of your team could take this list of
integration tests and add these automated tests to the integration test
package.*

## Use Case:* [A User should be able to CREATE a Product]*

### **Test case name: *[handleRequest_savedProduct_returnsProductInResult()]***

**Acceptance criteria:**

1. If the product dao receives a good request to save a product, a valid product is returned.

**Endpoint(s) tested:**

1. CreateAProductActivity

**GIVEN (Preconditions):**

1. A valid Product object
2. A good request from CreateAProductRequest

**WHEN (Action(s)):**

1. The CreateAProductResult is returned from the CreateAProductActivity

**THEN (Verification steps):**

1. The productId is not null
2. The result name equals the request name
3. The result quantity equals the request quantity
4. The result price equals the request price

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to GET a Product]*

### **Test case name: *[handleRequest_savedProductFound_returnsProductInResult()]***

**Acceptance criteria:**

1. If the product dao receives a valid product id, the corresponding product object is returned.

**Endpoint(s) tested:**

1. GetAProductActivity

**GIVEN (Preconditions):**

1. A valid Product object
2. A good request from GetAProductRequest with a valid productId

**WHEN (Action(s)):**

1. The GetAProductResult is returned from the GetAProductActivity

**THEN (Verification steps):**

1. The result product id retrieves the unmodified corresponding Product object from the table.

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to UPDATE a Product]*

### **Test case name: *[handleRequest_goodRequest_updatesProductName()]***

**Acceptance criteria:**

1. If the product dao receives a valid product id and attribute(s) to update, the corresponding product object is
updated in the product table.

**Endpoint(s) tested:**

1. UpdateAProductActivity

**GIVEN (Preconditions):**

1. A valid Product object
2. A valid Product attribute=('product name') to update
3. A good request from UpdateAProductRequest with a valid productId

**WHEN (Action(s)):**

1. The UpdateAProductResult is returned from the UpdateAProductActivity

**THEN (Verification steps):**

1. The result product id equals the request product id
2. The result name equals the request name
3. The result quantity equals the request quantity
4. The result price equals the request price

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to DELETE a Product]*

### **Test case name: *[handleRequest_requestedProductFound_returnsNullProductInResult()]***

**Acceptance criteria:**

1. If the product dao receives a valid product id, the corresponding product object is deleted in the product table. A
null value is returned.

**Endpoint(s) tested:**

1. DeleteAProductActivity

**GIVEN (Preconditions):**

1. A valid Product object
2. A valid productId to delete corresponding product object

**WHEN (Action(s)):**

1. The DeleteAProductResult is returned null

**THEN (Verification steps):**

1. The result product object is null

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to CREATE a Client]*

### **Test case name: *[handleRequest_savedClient_returnsClientInResult()]***

**Acceptance criteria:**

1. If the client doa receives a good request to save a client, a valid client object is returned

**Endpoint(s) tested:**

1. CreateClientActivity

**GIVEN (Preconditions):**

1. A valid Client object
2. A good request from CreateClientRequest

**WHEN (Action(s)):**

1. The CreateClientResult is returned from the CreateClientActivity

**THEN (Verification steps):**

1. The returned Client contains a unique clientId that has been generated
2. The result client name equals the request client name
3. The result client email equals the request client email
4. The result client phone number equals the request client phone number
5. The returned Client contains a unique inventoryId that has been generated

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to GET a Client]*

### **Test case name: *[handleRequest_savedClientFound_returnsClientInResult()]***

**Acceptance criteria:**

1. If the client dao receives a valid client id, the corresponding product is returned from the table.

**Endpoint(s) tested:**

1. GetClientActivity

**GIVEN (Preconditions):**

1. A valid Client object
2. A valid clientId to retrieve the corresponding client object

**WHEN (Action(s)):**

1. The GetClientResult is returned from the GetClientActivity

**THEN (Verification steps):**

1. The result client id retrieves the unmodified corresponding Client object from the table.

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to UPDATE a Client]*

### **Test case name: *[handleRequest_goodRequest_updatesClientName()]***

**Acceptance criteria:**

1. If the client dao receives a valid client id and attribute(s) to update, the corresponding client object is updated
in the product table.

**Endpoint(s) tested:**

1. UpdateAClientActivity

**GIVEN (Preconditions):**

1. A valid Client object
2. A valid Client attribute=('client name') to update
3. A good request from UpdateAClientRequest with a valid clientId

**WHEN (Action(s)):**

1. The UpdateAClientResult is returned from the UpdateAClientActivity

**THEN (Verification steps):**

1. The result client id equals the request client id
2. The result name equals the request name
3. The result email equals the request email
4. The result phone number equals the request phone number

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to GET an Inventory]*

### **Test case name: *[handleRequest_savedInventoryFound_returnsInventoryInResult()]***

**Acceptance criteria:**

1. If the inventory dao receives a valid client id, the client's unique inventory id returns the corresponding
inventory object from the table.

**Endpoint(s) tested:**

1. GetClientInventoryActivity

**GIVEN (Preconditions):**

1. A valid Inventory object, populated with a Product object(s)
2. A valid Client object, that holds corresponding inventoryId
3. A good request from GetAInventoryRequest with a valid clientId

**WHEN (Action(s)):**

1. The GetClientInventoryResult is returned from the GetClientInventoryActivity

**THEN (Verification steps):**

1. The result inventory object is an unmodified list of product objects that corresponds with the request
client object's inventory id.

**Is there any clean-up needed for this test?**

1. No

## Use Case:* [A User should be able to UPDATE an Inventory]*

### **Test case name: *[handleRequest_goodRequestToAddAProduct_addsProductToInventory()]***

**Acceptance criteria:**

1. If the inventory dao receives a valid client id, product id, inventory id and a string='add' the requested product is
added to that client's inventory.

**Endpoint(s) tested:**

1. UpdateInventoryActivity

**GIVEN (Preconditions):**

1. A valid Client object
2. A valid Inventory object that belongs to the Client
3. A valid Product object to add to the Inventory
4. A good request from UpdateInventoryActivity to 'add' product

**WHEN (Action(s)):**

1. The UpdateInventoryResult is returned from the UpdateInventoryActivity

**THEN (Verification steps):**

1. The result inventory object contains a new product object that corresponds to the product id that was passed

**Is there any clean-up needed for this test?**

1. No

### **Test case name: *[handleRequest_goodRequestToRemoveAProduct_removesProductFromInventory()]***

**Acceptance criteria:**

1. If the inventory dao receives a valid client id, product id, inventory id and a string='remove' the requested product 
is removed from the client's inventory.

**Endpoint(s) tested:**

1. UpdateInventoryActivity

**GIVEN (Preconditions):**

1. A valid Client object
2. A valid Inventory object that belongs to the Client
3. A valid Product object to remove from the Inventory
4. A good request from UpdateInventoryActivity to 'remove' product

**WHEN (Action(s)):**

1. The UpdateInventoryResult is returned from the UpdateInventoryActivity

**THEN (Verification steps):**

1. The result inventory object is returned minus the product object representing the product id that was passed

**Is there any clean-up needed for this test?**

1. No

# Manual Front-end Test Plan

*List the key manual test cases that you will perform to verify the full
end-to-end functionality of your project. Your integration tests will verify
that your service is working properly, but you will also need to ensure that
your web pages integrate with them properly. Use your judgment about which cases
to cover in your manual tests, as they’re much more costly to execute. But you
should cover the key operations that your customers will use the application
for.*

*The goal should be that any one of your team members could take this list of
Preconditions, Actions and Verification Steps and run all of these manual tests,
then report any bugs that are observed.*

## Use Case:* [use case name]*

### **Manual Test Case: *[description of case being tested]***

**GIVEN (Preconditions):**

1. *(List the conditions that must be true for the test case to take place.
These may include seed data and/or manual steps to perform during the test—be
clear which is which)*

**WHEN (Action(s)):**

1. *(List the steps that we’re actually testing to verify that they work
correctly)*

**THEN (Verification steps):**

1. *(List the steps to verify that the expected behavior actually happens,
include any relevant invariants here as well. These can be steps to view other
pages and/or inspect data directly in the database. Try to make the steps to
execute the test as straightforward/simple as possible to save time.)*
