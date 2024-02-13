package com.rahulsaraswat.OrderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long productId;
    private int quantity;
    private Instant orderDate;
    private String status;
    private long price;
}
