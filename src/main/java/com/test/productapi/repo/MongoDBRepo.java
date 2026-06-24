package com.test.productapi.repo;

import com.test.productapi.model.Product;
import com.test.productapi.model.dto.ProductResponse;
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
        return mongo.find(query, Product.class);
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return Optional.ofNullable(mongo.findById(id, Product.class));
    }

    @Override
    public void createProduct(Product product) {
        mongo.insert(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Query query = new Query(Criteria.where("_id").is(productId));
        mongo.remove(query, Product.class);
    }

    @Override
    public void updateProduct(Product product) {
        mongo.save(product);
    }
}
