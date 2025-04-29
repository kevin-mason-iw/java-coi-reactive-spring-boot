package com.coi.workshop.controller;

import com.coi.workshop.model.report.InventoryReport;
import com.coi.workshop.model.report.SalesReport;
import com.coi.workshop.service.ReportingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping(path = "/sales")
    public ResponseEntity<SalesReport> getSalesReport() {
        SalesReport salesReport = reportingService.buildSalesReport();
        return new ResponseEntity<>(salesReport, HttpStatus.OK);
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<String> getInventoryReport() {
        String inventoryReport = reportingService.buildInventoryReport();
        return new ResponseEntity<>(inventoryReport, HttpStatus.OK);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<String> getCustomerReport() {
        String s = reportingService.buildCustomerReport();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
