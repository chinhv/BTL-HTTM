package com.example.adminhttm.service;

import com.example.adminhttm.entities.Favor;
import com.example.adminhttm.entities.Product;
import com.example.adminhttm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FavorService {
    Favor create(Favor favor);

    Favor retrieve(Integer id);

    void update(Integer id, Favor favor);

    void delete(Integer id);

    List<Favor> findAll();

    Page<Favor> findAll1(Pageable pageable);

    List<Favor> doSearch(String keyword);

    Page<Favor> doSearch1(String keyword, Pageable pageable);

    Favor findByName(String name);
}
