package com.rahulsaraswat.OrderService.repository;

import com.rahulsaraswat.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
