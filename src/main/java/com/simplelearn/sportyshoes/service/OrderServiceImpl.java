package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.OrderNotFoundException;
import com.simplelearn.sportyshoes.exceptions.ProductOrOrderNotFoundException;
import com.simplelearn.sportyshoes.model.Order;
import com.simplelearn.sportyshoes.model.Product;
import com.simplelearn.sportyshoes.repository.OrderRepository;
import com.simplelearn.sportyshoes.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(int id) throws OrderNotFoundException {
        try{
            Optional<Order> order = orderRepository.findById(id);
            return order.orElse(null);
        }catch (Exception e){
            throw new OrderNotFoundException();
        }
    }

    @Override
    public List<Order> getOrdersByCategory(String category) {
        TypedQuery<Order> theQuery = entityManager.createQuery("SELECT o FROM Order o JOIN o.product p WHERE p.category = :requestedCategory", Order.class);
        theQuery.setParameter("requestedCategory", category);
        return theQuery.getResultList();
    }

    @Override
    public List<Order> getOrdersByDateAndTime(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Order> addOrder(int productId) throws ProductOrOrderNotFoundException {
        try{
            Order order = new Order();
            order.setDateAndTime(LocalDateTime.now());
            Optional<Product> product = productRepository.findById(productId);
            product.ifPresent(order::setProduct);
            orderRepository.save(order);
            return orderRepository.findAll();
        }catch (Exception e) {
            throw new ProductOrOrderNotFoundException();
        }
    }

    @Override
    public Order updateOrder(int orderId, int productId) throws OrderNotFoundException {
         try {
             Optional<Order> order = orderRepository.findById(orderId);
             Optional<Product> productNew = productRepository.findById(productId);
             order.get().setProduct(productNew.get());
             return orderRepository.findById(orderRepository.findAll().size()).get();
        }catch (Exception e){
             throw new OrderNotFoundException();
         }
    }

    @Override
    public List<Order> deleteOrder(int id) throws OrderNotFoundException {
        try{
            Optional<Order> orderToBeDeleted = orderRepository.findById(id);
            orderRepository.delete(orderToBeDeleted.get());
            return orderRepository.findAll();
        }catch (Exception e) {
            throw new OrderNotFoundException();
        }
    }
}
