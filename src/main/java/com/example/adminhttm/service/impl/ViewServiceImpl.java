package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.entities.View;
import com.example.adminhttm.repository.ViewRepository;
import com.example.adminhttm.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewRepository viewRepository;

    @Override
    public View create(View entity) {
        return viewRepository.save(entity);
    }

    @Override
    public View retrieve(Integer id) {
        return viewRepository.findById(id).orElse(null);
    }

    @Override
    public void update(View entity, Integer id) {
        viewRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        viewRepository.deleteById(id);
    }

    @Override
    public Page<View> doSearch(String keyword, Pageable pageable) {
        return viewRepository.doSearch(keyword, pageable);
    }

    @Override
    public Page<View> findAll(Pageable pageable) {
        return viewRepository.findAll(pageable);
    }
}
