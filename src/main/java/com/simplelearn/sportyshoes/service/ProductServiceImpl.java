package com.simplelearn.sportyshoes.service;

import com.simplelearn.sportyshoes.exceptions.ProductNotFoundException;
import com.simplelearn.sportyshoes.model.Product;
import com.simplelearn.sportyshoes.repository.ProductRepository;
import io.netty.util.internal.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        try{
            Optional<Product> product = productRepository.findById(id);
            return product.orElse(null);
        }catch (Exception e){
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        TypedQuery<Product> theQeuery = entityManager.createQuery("FROM Product WHERE category=:requestedCategory", Product.class);
        theQeuery.setParameter("requestedCategory", category);
        return theQeuery.getResultList();
    }

    @Override
    public List<Product> addProduct(Product product) throws ProductNotFoundException {
        try{
            productRepository.save(product);
            return productRepository.findAll();
        }catch (Exception e) {
            throw new ProductNotFoundException();
        }

    }

    @Override
    public Product updateProduct(int id, Product product) throws ProductNotFoundException {
        try{
            Optional<Product> productToBeUpdated = productRepository.findById(id);

            if(!StringUtil.isNullOrEmpty(product.getProductName())){
                productToBeUpdated.get().setProductName(product.getProductName());
            }
            if(!StringUtil.isNullOrEmpty(product.getCategory())){
                productToBeUpdated.get().setCategory(product.getCategory());
            }
            if(product.getPrice() > 0.0f){
                productToBeUpdated.get().setPrice(product.getPrice());
            }
            if(product.getQuantity() > 0){
                productToBeUpdated.get().setQuantity(product.getQuantity());
            }
            if(StringUtil.isNullOrEmpty(product.getCategory())){
                productToBeUpdated.get().setCategory(product.getCategory());
            }
            productRepository.save(productToBeUpdated.get());
            return productRepository.findById(id).get();
        }catch (Exception e){
            throw new ProductNotFoundException();
        }
    }

    @Override
    public List<Product> deleteProduct(int id) throws ProductNotFoundException {
        try{
            Optional<Product> productToBeDeleted = productRepository.findById(id);
            productRepository.delete(productToBeDeleted.get());
            return productRepository.findAll();
        }catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }
}
