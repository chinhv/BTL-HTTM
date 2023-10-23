package com.example.adminhttm.service;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.entities.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViewService {
    View create(View entity);

    View retrieve(Integer id);

    void update(View entity, Integer id);

    void delete(Integer id);

    Page<View> doSearch(String keyword, Pageable pageable);

    Page<View> findAll(Pageable pageable);
}
