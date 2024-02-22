package com.rahulsaraswat.OrderService.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private long productID;
    private String productName;
    private int price;
    private int quantity;
}
