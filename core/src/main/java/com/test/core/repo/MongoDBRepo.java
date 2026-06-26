package com.test.core.repo;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.test.core.model.Product;
import com.test.core.model.ProductDocument;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.database", havingValue = "mongodb") // creates the bean only if the property is mongodb
public class MongoDBRepo implements ProductRepo{
    private final MongoTemplate mongo;

    public MongoDBRepo(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    @Override
    public List<Product> findAllProducts(int size, int page) {
        Query query = new Query()
                .with(Sort.by(Sort.Direction.ASC, "id"))
                .skip((long) page * size)
                .limit(size);
        return mongo.find(query, ProductDocument.class).stream().map(ProductDocument::toProduct).toList();
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return Optional.ofNullable(mongo.findById(id, ProductDocument.class))
                .map(ProductDocument::toProduct);
    }

    @Override
    public boolean findProductByProductName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongo.exists(query, ProductDocument.class);
    }

    @Override
    public void createProduct(Product product) {
        mongo.insert(product);
    }

    @Override
    public long deleteProduct(String productId) {
        Query query = new Query(Criteria.where("_id").is(productId));
        DeleteResult result = mongo.remove(query, ProductDocument.class);
        return result.getDeletedCount();
    }

    @Override
    public long updateProduct(Product product) {
        Query query = new Query(Criteria.where("_id").is(product.getId()));
        Update update = new Update()
                .set("name", product.getName())
                .set("description", product.getDescription())
                .set("brand", product.getBrand())
                .set("category", product.getCategory())
                .set("price", product.getPrice())
                .set("product_available", product.getProductAvailable())
                .set("stock_quantity", product.getStockQuantity());
        UpdateResult result = mongo.updateFirst(query, update, ProductDocument.class);
        return result.getModifiedCount();
    }
}
