package com.coi.workshop.service;

import com.coi.workshop.client.CustomerClient;
import com.coi.workshop.client.InventoryClient;
import com.coi.workshop.client.OrderClient;
import com.coi.workshop.client.ProductClient;
import com.coi.workshop.exceptions.InventoryNotFoundException;
import com.coi.workshop.model.Customer;
import com.coi.workshop.model.OrderItems;
import com.coi.workshop.model.Sales;
import com.coi.workshop.model.Inventory;
import com.coi.workshop.model.Order;
import com.coi.workshop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ReportingService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderClient orderClient;
    private final InventoryClient inventoryClient;

    public ReportingService(CustomerClient customerClient,
                            ProductClient productClient,
                            OrderClient orderClient,
                            InventoryClient inventoryClient) {
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderClient = orderClient;
        this.inventoryClient = inventoryClient;
    }

    public List<Sales> buildSalesReport() {
        // TODO - build sales report to detail what the top sellers are, include total sold, product name, total revenue
        // TODO - Iterate through all orders and order by the SKU with highest total revenue
        Map<String, Integer> productSales = new HashMap<>();
        List<Order> orders = orderClient.getAllOrders();
        for (Order order : orders) {
            for (OrderItems orderItem : order.orderItems()) {
                String sku = orderItem.sku();
                try {
                    Inventory inventory = inventoryClient.getSku(sku);
                    String productId = inventory.productId();
                    if (productSales.containsKey(productId)) {
                        Integer currentValue = productSales.get(productId);
                        Integer newValue = currentValue + orderItem.quantity();
                        productSales.replace(productId, newValue);
                    } else {
                        productSales.put(productId, orderItem.quantity());
                    }
                } catch (InventoryNotFoundException e) {
                    log.warn("inventory not found: {}", sku);
                }
            }
        }
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

        salesList.sort((s1, s2) -> Double.compare(s2.totalRevenue(), s1.totalRevenue()));
        return salesList;
    }

    public String buildCustomerReport() {
        // TODO - build management report to identify most profitable customer, customer name, email, total spent
        // TODO - iterate through all orders and order by customerID
        // TODO - order though new list and get customer details and
        Customer customer = customerClient.getCustomer("CUST-1015");
        // TODO - build the ManagementReport object
        return customer.firstName() + " " +  customer.lastName();
    }

    public String buildInventoryReport() {
        // TODO - build inventory report to identify how much items to order in the next delivery
        // TODO - update below to get a list of all orders and detail how many items have been sold and how much is in inventory
        Inventory inventory = inventoryClient.getSku("9012-880-772");
        // TODO - iterate through the orders and total how SKU have been sold
        // TODO - iterate through new SKU map and build inventory report using the current inventory data
        // TODO - build Inventory Report object
        return "inventory report" + inventory.toString();
    }
}
