package com.simplelearn.sportyshoes.repository;

import com.simplelearn.sportyshoes.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
