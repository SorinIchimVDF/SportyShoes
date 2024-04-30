package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.OrderNotFoundException;
import com.simplelearn.sportyshoes.exceptions.ProductOrOrderNotFoundException;
import com.simplelearn.sportyshoes.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int id) throws OrderNotFoundException;
    List<Order> getOrdersByCategory(String category);
    List<Order> getOrdersByDateAndTime(LocalDate date);
    List<Order> addOrder(int productId) throws ProductOrOrderNotFoundException;
    Order updateOrder(int orderId, int productId) throws OrderNotFoundException;
    List<Order> deleteOrder(int id) throws OrderNotFoundException;
}
