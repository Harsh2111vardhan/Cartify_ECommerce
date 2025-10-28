package com.store.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.onlinestore.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Basic CRUD operations provided by Spring Data JPA
}