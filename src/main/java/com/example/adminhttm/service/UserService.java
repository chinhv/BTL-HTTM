package com.example.adminhttm.service;

import com.example.adminhttm.entities.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User retrieve(Integer id);

    void update(Integer id, User user);

    void delete(Integer id);

    List<User> findAll();

    List<User> doSearch(String keyword);

    User findByEmail(String email);
}
