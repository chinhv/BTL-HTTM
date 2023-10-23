package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.Product;
import com.example.adminhttm.repository.ProductRepository;
import com.example.adminhttm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product retrieve(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}
