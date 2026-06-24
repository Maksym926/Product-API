package com.test.productapi;


import com.test.productapi.model.Product;
import com.test.productapi.model.dto.PageResponse;
import com.test.productapi.model.dto.ProductRequest;
import com.test.productapi.model.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<PageResponse<ProductResponse>> getProduct(@RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "0") int page ){
        return ResponseEntity.ok(productService.getProducts(size, page));
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
    @PatchMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductRequest updates) {
        ProductResponse response = productService.updateProductPartially(productId, updates);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> replaceProduct(@PathVariable String productId, @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProductEntirely(productId, productRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted with id: " + productId);
    }
}
