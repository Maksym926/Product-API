package com.test.core.config;

import com.test.core.repo.ProductRepo;
import com.test.core.service.ProductService;
import com.test.core.service.ProductServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ProductServiceI productService(ProductRepo productRepo) {
        return new ProductService(productRepo);
    }

}
