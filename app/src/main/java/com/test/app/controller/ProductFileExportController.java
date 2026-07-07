package com.test.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.core.service.ProductFileExportService;

@RestController
@RequestMapping("/api/products")
public class ProductFileExportController {
    @Autowired
    private ProductFileExportService productFileExportService;
    @GetMapping("/export.csv")
    public void exportProductCSVFile(@RequestParam(defaultValue = "products.csv") String filePath){
        productFileExportService.exportProducts(filePath);
    }
}
