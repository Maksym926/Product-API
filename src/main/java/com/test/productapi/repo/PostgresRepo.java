package com.test.productapi.repo;

import com.test.productapi.model.Product;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.database", havingValue = "postgres") // creates the bean only if the property is postgres
public class PostgresRepo implements ProductRepo{

    private final JdbcClient jdbc;

    public PostgresRepo(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }
    @Override
    public List<Product> findAllProducts(int size, int page)
    {
        int offset = page * size;
        return jdbc.sql("SELECT * FROM products ORDER BY id LIMIT :limitVal OFFSET :offsetVal")
                .param("limitVal", size)
                .param("offsetVal", offset)
                .query(Product.class)
                .list();
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return jdbc.sql("SELECT * FROM products WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    @Override
    public void createProduct(Product product) {
        String id = jdbc.sql("INSERT INTO products (name, description, brand, category, price, product_available, stock_quantity)" +
                        " VALUES (:name, :description, :brand, :category, :price, :product_available, :stock_quantity)" +
                        " RETURNING id")
                .param("name", product.getName())
                .param("description", product.getDescription())
                .param("brand", product.getBrand())
                .param("category", product.getCategory())
                .param("price", product.getPrice())
                .param("product_available", product.getProductAvailable())
                .param("stock_quantity", product.getStockQuantity())
                .query(String.class)
                .single();
        product.setId(id);


    }

    @Override
    public void deleteProduct(String productId) {
        jdbc.sql("DELETE FROM products WHERE id = :id")
                .param("id", productId)
                .update();
    }

    @Override
    public void updateProduct(Product product) {

        jdbc.sql("UPDATE products SET name = :name, description = :description, brand = :brand, category = :category, price = :price" +
                ", product_available = :product_available, stock_quantity = :stock_quantity WHERE id = :id")
                .param("name", product.getName())
                .param("description", product.getDescription())
                .param("brand", product.getBrand())
                .param("category", product.getCategory())
                .param("price", product.getPrice())
                .param("product_available", product.getProductAvailable())
                .param("stock_quantity", product.getStockQuantity())
                .param("id", product.getId())
                .update();


    }
}
