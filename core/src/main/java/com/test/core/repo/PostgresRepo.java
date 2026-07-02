package com.test.core.repo;

import com.test.core.model.Product;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@ConditionalOnProperty(name = "app.database", havingValue = "mongodb") // creates the bean only if the property is mongodb
@Profile("postgres")
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
    public boolean findProductByProductName(String name) {
        int count = jdbc.sql("SELECT COUNT(*) FROM products WHERE name = :name")
                .param("name", name)
                .query(Integer.class)
                .single();
        return count > 0;
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
    public long deleteProduct(String productId) {
        return jdbc.sql("DELETE FROM products WHERE id = :id")
                .param("id", productId)
                .update();
    }

    @Override
    public long updateProduct(Product product) {

        return jdbc.sql("UPDATE products SET name = :name, description = :description, brand = :brand, category = :category, price = :price" +
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
