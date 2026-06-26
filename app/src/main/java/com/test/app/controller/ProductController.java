package com.test.app.controller;


import com.test.core.model.Product;
import com.test.core.model.dto.PageResponse;
import com.test.core.model.dto.ProductRequest;
import com.test.core.model.dto.ProductResponse;
import com.test.core.service.ProductServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductServiceI productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getProduct(@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "0") int page ){
        return ResponseEntity.ok(productService.getProducts(size, page));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductRequest updates) {
        ProductResponse response = productService.updateProductPartially(productId, updates);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> replaceProduct(@PathVariable String productId, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProductEntirely(productId, productRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted with id: " + productId);
    }
}
