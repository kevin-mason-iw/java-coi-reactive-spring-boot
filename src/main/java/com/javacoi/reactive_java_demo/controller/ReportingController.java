package com.javacoi.reactive_java_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportingController {

    @GetMapping(path = "/inventory")
    public String getInventoryReport(){
        return "Test Inventory Report";
    }

}
