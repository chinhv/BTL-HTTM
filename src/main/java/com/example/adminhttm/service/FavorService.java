package com.example.adminhttm.service;

import com.example.adminhttm.entities.Favor;

import java.util.List;

public interface FavorService {
    Favor create(Favor favor);

    Favor retrieve(Integer id);

    void update(Integer id, Favor favor);

    void delete(Integer id);

    List<Favor> findAll();

    List<Favor> doSearch(String keyword);

    Favor findByName(String name);
}
