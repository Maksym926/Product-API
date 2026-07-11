package com.test.core.service;

import com.maksym.csv.CSVWriter;
import com.test.core.exception.custom.ExistingProductException;
import com.test.core.exception.custom.ItemNotFoundException;
import com.test.core.model.Product;
import com.test.core.model.dto.PageResponse;
import com.test.core.model.dto.ProductRequest;
import com.test.core.model.dto.ProductResponse;
import com.test.core.repo.ProductRepo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;



public class ProductService implements ProductServiceI {


    private final ProductRepo productRepo;
    private final CSVWriter csvWriter;
    public ProductService(ProductRepo productRepo, CSVWriter csvWriter) {
        this.productRepo = productRepo;
        this.csvWriter = csvWriter;
    }


    public PageResponse<ProductResponse> getProducts(int size, int page) {
        List<ProductResponse> products =  productRepo.findAllProducts(size, page)
                .stream()
                .map(ProductResponse::from)
                .toList();
        return new PageResponse<>(products, page, size);
    }

    public Product getProductById(String productId) {
        return productRepo.findProductById(productId).orElseThrow(
                () -> new ItemNotFoundException(productId)
        );
    }

    public ProductResponse createProduct(ProductRequest productRequest) {

        if(productRepo.findProductByProductName(productRequest.name())){

            throw new ExistingProductException(productRequest.name());
        }

        Product product = new Product(
                productRequest.name(),
                productRequest.description(),
                productRequest.brand(),
                productRequest.category(),
                productRequest.price(),
                productRequest.productAvailable(),
                productRequest.stockQuantity());
        productRepo.createProduct(product);
        return ProductResponse.from(product);
    }

    public ProductResponse updateProductPartially(String productId, ProductRequest updates) {
        Product existingProduct = productRepo.findProductById(productId).orElseThrow(
                () -> new ItemNotFoundException(productId)
        );

        if (updates.name() != null)             existingProduct.setName(updates.name());
        if (updates.description() != null)      existingProduct.setDescription(updates.description());
        if (updates.brand() != null)            existingProduct.setBrand(updates.brand());
        if (updates.category() != null)         existingProduct.setCategory(updates.category());
        if (updates.price() != null)            existingProduct.setPrice(updates.price());
        if (updates.productAvailable() != null) existingProduct.setProductAvailable(updates.productAvailable());
        if (updates.stockQuantity() != null)    existingProduct.setStockQuantity(updates.stockQuantity());

        productRepo.updateProduct(existingProduct);
        return ProductResponse.from(existingProduct);
    }
    public ProductResponse updateProductEntirely(String productId, ProductRequest productRequest) {
        Product existingProduct = productRepo.findProductById(productId).orElseThrow(
                () -> new ItemNotFoundException(productId)
        );
        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setBrand(productRequest.brand());
        existingProduct.setCategory(productRequest.category());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setProductAvailable(productRequest.productAvailable());
        existingProduct.setStockQuantity(productRequest.stockQuantity());
        long rowEffected = productRepo.updateProduct(existingProduct);
        if(rowEffected == 0){
            throw new ItemNotFoundException(productId);
        }

        return ProductResponse.from(existingProduct);
    }

    public void deleteProduct(String productId) {
        long rowEffected = productRepo.deleteProduct(productId);
        if(rowEffected == 0){
            throw new ItemNotFoundException(productId);
        }

    }
    @Override
    public String exportProducts() {
        var products = productRepo.findAllProducts(Integer.MAX_VALUE, 0);
        StringWriter stringWriter = new StringWriter();
        try {

            csvWriter.write(
                    products.stream(),
                    p -> new String[]{
                            p.getId(),
                            p.getName(),
                            p.getDescription(),
                            p.getBrand(),
                            p.getCategory(),
                            String.valueOf(p.getPrice()),
                            String.valueOf(p.getProductAvailable()),
                            String.valueOf(p.getStockQuantity())
                    },
                    false,
                    stringWriter
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to export products to CSV", e);
        }
        return stringWriter.toString();
    }

}
