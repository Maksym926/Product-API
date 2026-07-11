package com.test.core.config;

import com.maksym.csv.CSVWriter;
import com.maksym.csv.CSVWriterImpl;
import com.test.core.repo.ProductRepo;
import com.test.core.service.ProductService;
import com.test.core.service.ProductServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ProductServiceI productService(ProductRepo productRepo, CSVWriter csvWriter) {
        return new ProductService(productRepo, csvWriter);
    }
    @Bean
    public CSVWriter csvWriter(){
        return new CSVWriterImpl();
    }




}
