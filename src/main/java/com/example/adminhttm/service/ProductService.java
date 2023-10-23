package com.example.adminhttm.service;

import com.example.adminhttm.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product retrieve(Integer id);
}
