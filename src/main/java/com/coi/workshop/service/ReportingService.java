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
import com.coi.workshop.model.report.InventoryReport;
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

    /**
     * TASK 2. Sales report is too slow - can this be sped up?
     * @return
     */
    public List<Sales> buildSalesReport() {
        // TODO - build sales report to detail what the top sellers are, include total sold, product name, total revenue
        // TODO - Iterate through all orders and order by the SKU with highest total revenue
        Map<String, Integer> productSales = new HashMap<>();
        List<Order> orders = orderClient.getAllOrders();
        List<CompletableFuture<Void>> cfs = new ArrayList<>();
        for (Order order : orders) {
            for (OrderItems orderItem : order.orderItems()) {
                String sku = orderItem.sku();
                cfs.add(CompletableFuture.runAsync(() -> {
                    try {
                        Inventory inventory = inventoryClient.getSku(sku);
                        String productId = inventory.productId();
                        synchronized (productSales) {
                            productSales.merge(productId, orderItem.quantity(), Integer::sum);
                        }
                    } catch (InventoryNotFoundException e) {
                        log.warn("inventory not found: {}", sku);
                    }
                }));
            }
        }
        cfs.forEach(CompletableFuture::join);
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

    /**
     * TASK 1.create an inventory report
     * @return Inventory
     */
    public List<InventoryReport> buildInventoryReport() {
        List<Order> orderList = orderClient.getAllOrders();

        Map<String, Integer> itemsSold = new HashMap<>();

        for (Order order : orderList){
            for (OrderItems orderItem : order.orderItems()){
                String sku = orderItem.sku();
                int quantity = orderItem.quantity();
                if (itemsSold.containsKey(sku)){
                    Integer currentSold = itemsSold.get(sku);
                    Integer updatedSold = currentSold + quantity;
                    itemsSold.replace(sku, updatedSold);
                } else {
                    itemsSold.put(sku, quantity);
                }
            }
        }

        List<InventoryReport> inventoryReportList = new ArrayList<>();
        for (Map.Entry<String, Integer> itemSale : itemsSold.entrySet()) {
            String sku = itemSale.getKey();
            Integer totalSold = itemSale.getValue();

            Inventory inventoryItem = inventoryClient.getSku(sku);
            Product product = productClient.getProduct(inventoryItem.productId());

            InventoryReport inventoryReport = new InventoryReport(sku, product.title(), totalSold, inventoryItem.stockAmount());
            inventoryReportList.add(inventoryReport);
        }

        return inventoryReportList;
    }
}
