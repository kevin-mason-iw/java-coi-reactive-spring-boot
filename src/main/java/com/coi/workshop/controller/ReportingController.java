package com.coi.workshop.controller;

import com.coi.workshop.model.SalesReport;
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
        // TODO - make call to service to get report data. Return data as JSON
        SalesReport salesReport = reportingService.buildSalesReport();
        return new ResponseEntity<>(salesReport, HttpStatus.OK);
    }

    @GetMapping(path = "/inventory")
    public String getInventoryReport(){
        // TODO - make call to service to get report data. Return data as JSON
        String s = reportingService.buildInventoryReport();
        return s;
    }

    @GetMapping(path = "/management")
    public String getManagementReport(){
        String s = reportingService.buildManagementReport();
        return s;
    }

}
