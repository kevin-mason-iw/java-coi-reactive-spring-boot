package com.coi.workshop.service;

import com.coi.workshop.client.CustomerClient;
import com.coi.workshop.client.InventoryClient;
import com.coi.workshop.client.OrderClient;
import com.coi.workshop.client.ProductClient;
import com.coi.workshop.exceptions.InventoryNotFoundException;
import com.coi.workshop.model.Sales;
import com.coi.workshop.model.SalesReport;
import com.coi.workshop.pojo.Customer;
import com.coi.workshop.pojo.Inventory;
import com.coi.workshop.pojo.Order;
import com.coi.workshop.pojo.OrderItems;
import com.coi.workshop.pojo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

    public String buildManagementReport() {
        // TODO - build management report to identify most profitable customer, customer name, email, total spent
        List<Customer> customers = customerClient.getCustomers();
        // TODO - iterate through all orders and order by customerID
        // TODO - order though new list and get customer details and
        // TODO - build the ManagementReport object
        String s = customers.get(0).firstName();
        return s;
    }

    public SalesReport buildSalesReport() {
        // TODO - build sales report to detail what the top sellers are, include total sold, product name, total revenue
        // TODO - Iterate through all orders and order by the SKU with highest total revenue
        Map<String, Integer> productSales = new HashMap<>();
        List<Order> orders = orderClient.getOrders();
        for (Order order : orders) {
            for (OrderItems orderItem: order.orderItems()) {
                String sku = orderItem.sku();
                try {

                    Inventory inventory = inventoryClient.getInventory(sku);
                    String productId = inventory.productId();
                    if (productSales.containsKey(productId)) {
                        Integer currentValue = productSales.get(productId);
                        Integer newValue = currentValue + orderItem.quantity();
                        productSales.replace(productId, newValue);
                    } else {
                        productSales.put(productId, orderItem.quantity());
                    }
                } catch (InventoryNotFoundException e){
                    log.warn("inventory not found: {}", sku);
                }
            }
        }
        // TODO - build SalesReport object
        List<Sales> salesList = new ArrayList<>();
        // TODO - Iterate through new list and get the product title for each SKU
        for (Map.Entry<String, Integer> productSale : productSales.entrySet()) {
            String productId = productSale.getKey();
            Integer totalSold = productSale.getValue();

            Product product = productClient.getProduct(productId);
            Double totalSales = product.price() * totalSold;
            Sales sales = new Sales(productId, product.title(), totalSold, totalSales);
            salesList.add(sales);
        }
        return new SalesReport(salesList);
    }

    public String buildInventoryReport(){
        // TODO - build inventory report to identify how much items to order in the next delivery
        // TODO - update below to get a list of all orders and detail how many items have been sold and how much is in inventory
        Inventory inventory = inventoryClient.getInventory("0000-111-222");
        // TODO - iterate through the orders and total how SKU have been sold
        // TODO - iterate through new SKU map and build inventory report using the current inventory data
        // TODO - build InventoryReport Object.
        return inventory.productId();
    }
}
