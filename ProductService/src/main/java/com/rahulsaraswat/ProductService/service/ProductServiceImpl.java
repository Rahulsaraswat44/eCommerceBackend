package com.rahulsaraswat.ProductService.service;

import com.rahulsaraswat.ProductService.entity.Product;
import com.rahulsaraswat.ProductService.exception.ProductServiceCustomException;
import com.rahulsaraswat.ProductService.model.ProductRequest;
import com.rahulsaraswat.ProductService.model.ProductResponse;
import com.rahulsaraswat.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding the product in database");
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();

        productRepository.save(product);

        log.info("product added...");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productID) {
        log.info("getting product by ID: {}" , productID);
        Product product = productRepository.findById(productID)
                .orElseThrow(() ->
                        new ProductServiceCustomException(
                                "Product with id not found",
                                "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = ProductResponse.builder().build();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productID, int quantity) {
        log.info("reducing quantity of product with id: {} and quantity to be reduce is: {}", productID, quantity);
        Product product = productRepository.findById(productID)
                .orElseThrow(() ->
                        new ProductServiceCustomException(
                                "Product with id not found",
                                "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product quantity is not sufficient",
                    "QUANTITY_INSUFFICIENT");
        } else {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
        }
    }
}
