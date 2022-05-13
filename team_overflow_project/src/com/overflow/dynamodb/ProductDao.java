package com.overflow.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.overflow.dynamodb.models.Product;
import com.overflow.exceptions.ProductNotFoundException;

import javax.inject.Inject;
import java.util.List;

public class ProductDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates a ProductDao object.
     *
     * @param dynamoDBMapper the {@link DynamoDBMapper} used to interact with the products table
     */
    @Inject
    public ProductDao(DynamoDBMapper dynamoDBMapper){ this.dynamoDBMapper = dynamoDBMapper;}

    /**
     * Returns the {@link Product} corresponding to the specified id.
     *
     * @param productId the Product ID
     * @return the stored Product, or null if none was found.
     */
    public Product getProduct(String productId) {
        Product product = this.dynamoDBMapper.load(Product.class, productId);

        if(product == null) {
            throw new ProductNotFoundException("Could not find product with id " + productId);
        }
        return product;
    }

    /**
     * Returns the {@link Product} corresponding to the specified id.
     *
     * @param product the Product to be saved to the DynamoDB table
     * @return the stored Product.
     */
    public Product saveProduct(Product product) {
        this.dynamoDBMapper.save(product);
        return product;
    }

    /**
     * Returns the {@link Product} corresponding to the specified id.
     *
     * @param productIdToDelete the Product ID
     */
    public void deleteProduct(String productIdToDelete) {
        Product productToDelete = getProduct(productIdToDelete);
        dynamoDBMapper.delete(productToDelete);
    }

    /**
     * Returns the {@Link List<Product>} corresponding to the Product table
     *
     * @param products any string passed to retrieve all Product objects (can be null)
     * @return a List of all available Products
     */
    public List<Product> getAllProducts(String products) {
        return dynamoDBMapper.scan(Product.class, new DynamoDBScanExpression());
    }
}
