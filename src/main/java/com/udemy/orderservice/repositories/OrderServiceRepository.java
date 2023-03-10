package com.udemy.orderservice.repositories;

import com.udemy.orderservice.entity.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {
}
