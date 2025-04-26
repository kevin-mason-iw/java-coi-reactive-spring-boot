package com.javacoi.reactive_java_demo.service;

import com.javacoi.reactive_java_demo.client.CustomerClient;
import com.javacoi.reactive_java_demo.client.InventoryClient;
import com.javacoi.reactive_java_demo.client.OrderClient;
import com.javacoi.reactive_java_demo.client.ProductClient;
import com.javacoi.reactive_java_demo.model.ManagementReport;
import com.javacoi.reactive_java_demo.pojo.Customer;
import com.javacoi.reactive_java_demo.pojo.Inventory;
import com.javacoi.reactive_java_demo.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    CustomerClient customerClient;
    ProductClient productClient;
    OrderClient orderClient;
    InventoryClient inventoryClient;

    public ReportingService(CustomerClient customerClient, ProductClient productClient, OrderClient orderClient, InventoryClient inventoryClient) {
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderClient = orderClient;
        this.inventoryClient = inventoryClient;
    }

    public String buildManagementReport(){
        // TODO - build management report to identify most profitable customer, customer name, email, total spent
        List<Customer> customers = customerClient.getCustomers();
        // TODO - iterate through all orders and order by customerID
        // TODO - order though new list and get customer details and
        // TODO - build the ManagementReport object
        String s = customers.getFirst().firstName();
        return s;
    }

    public String buildSalesReport(){
        // TODO - build sales report to detail what the top sellers are, include total sold, product name, total revenue
        Product product = productClient.getProduct("ABC-1234");
        // TODO - Iterate through all orders and order by the SKU with highest total revenue
        // TODO - Iterate through new list and get the product title for each SKU
        // TODO - build SalesReport object
        return product.title();
    }

    public String buildInventoryReport(){
        // TODO - build inventory report to identify how much items to order in the next delivery
        // TODO - update below to get a list of all orders and detail how many items have been sold and how much is in inventory
        Inventory inventory = inventoryClient.getInventory("0000-111-222");
        // TODO - itereate through the orders and total how SKU have been sold
        // TODO - iterate through new SKU map and build inventory report using the current inventory data
        // TODO - build InventoryReport Object.
        return inventory.productId();
    }
}
