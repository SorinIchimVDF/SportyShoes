package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.ProductNotFoundException;
import com.simplelearn.sportyshoes.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id) throws ProductNotFoundException;
    List<Product> getProductsByCategory(String category);
    List<Product> addProduct(Product product) throws ProductNotFoundException;
    Product updateProduct(int id, Product product) throws ProductNotFoundException;
    List<Product> deleteProduct(int id) throws ProductNotFoundException;
}
