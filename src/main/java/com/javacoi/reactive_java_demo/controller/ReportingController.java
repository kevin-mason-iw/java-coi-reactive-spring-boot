package com.javacoi.reactive_java_demo.controller;

import com.javacoi.reactive_java_demo.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportingController {

    @Autowired
    ReportingService reportingService;

    @GetMapping(path = "/sales")
    public String getSalesReport(){
        // TODO - make call to service to get report data. Return data as JSON
        String s = reportingService.buildSalesReport();
        return s;
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
