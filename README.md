# java-coi-reactive-spring-boot


## Case Study - Real Time Data Aggregation
We will be pulling data from 3 data sources to create an aggregated view of sales/order information for the business.
Fetch data from the sales database
Fetch data from the inventory database
Fetch data from the

### User Requirements
**As an** admin,
**I want to** see a real-time dashboard showing total sales, orders, and inventory status,
**So that** I can make informed business decisions quickly.
:white_tick: *Technical Implementation:* Fetch data from multiple microservices (Sales, Orders, Inventory) asynchronously using Java Futures and aggregate the results.

Reporting Exercise (Reactive Programming)

Service that builds an Detail order report for a client

Data Models;

**Orders;**
- OrderNumber
- CustomerID
- OrderItems
    - Sku
    - TotalPrice
    - Quantity
- Date

**Customer;**
- CustomerId
- FirstName
- LastName
- Email
- Telephone Number

**Product;**
- ProductID
- Title
- Price
- Description

**Inventory;**
- SKU
- ProductId
- Amount


## Clients
- OrderClient
- CustomerClient
- ProductClient
- InventoryClient (optional  if we have time)

## SUDO CODE
1. Iterate through list of orders
    1. Get full product details
    2. Get full customer details
    3. Build Report Line
2. Build Report object
3. Export to CSV


## Task List
1. Create non reactive solution
2. Create reactive solution
3. ????
4. How to get close to real time

## Todo List

- Create Repository (Kevin)
- Create boiler plater code (Kevin)
- Create Test Data (Waqas)
- Create Exercise Details / Worksheet (Waqas)
- Create Solution (both)
    - Non-reactive (Kevin)
    - Reactive (Waqas)
- Create ReadMe (Both)
- Create Slides (Kevin)