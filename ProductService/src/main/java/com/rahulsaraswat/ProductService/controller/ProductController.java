package com.rahulsaraswat.ProductService.controller;

import com.rahulsaraswat.ProductService.model.ProductRequest;
import com.rahulsaraswat.ProductService.model.ProductResponse;
import com.rahulsaraswat.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        Long productID = productService.addProduct(productRequest);
        return new ResponseEntity<>(productID, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse>  getProductById(@PathVariable("id") long productID ) {
        ProductResponse productResponse = productService.getProductById(productID);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productID,
                                        @RequestParam int quantity) {
        productService.reduceQuantity(productID, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
