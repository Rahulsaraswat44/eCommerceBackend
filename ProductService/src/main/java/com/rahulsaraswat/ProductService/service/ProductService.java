package com.rahulsaraswat.ProductService.service;

import com.rahulsaraswat.ProductService.model.ProductRequest;
import com.rahulsaraswat.ProductService.model.ProductResponse;
import org.springframework.stereotype.Service;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productID);

    void reduceQuantity(long productID, int quantity);
}
