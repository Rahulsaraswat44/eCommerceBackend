package com.rahulsaraswat.OrderService.external.client;

import com.rahulsaraswat.OrderService.external.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {
    @GetMapping("/{id}")
    ResponseEntity<ProductResponse>  getProductById(@PathVariable("id") long productID ) ;

        @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productID,
                                        @RequestParam int quantity);
}
