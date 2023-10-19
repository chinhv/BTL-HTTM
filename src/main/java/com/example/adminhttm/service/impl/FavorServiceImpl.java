package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.Favor;
import com.example.adminhttm.repository.FavorRepository;
import com.example.adminhttm.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavorServiceImpl implements FavorService {

    @Autowired
    private FavorRepository favorRepository;

    @Override
    public Favor create(Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public Favor retrieve(Integer id) {
        return favorRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, Favor favor) {
        favorRepository.save(favor);
    }

    @Override
    public void delete(Integer id) {
        favorRepository.deleteById(id);
    }

    @Override
    public List<Favor> findAll() {
        return favorRepository.findAll();
    }

    @Override
    public List<Favor> doSearch(String keyword) {
        return favorRepository.doSearch(keyword);
    }

    @Override
    public Favor findByName(String name) {
        return favorRepository.findByName(name);
    }
}
