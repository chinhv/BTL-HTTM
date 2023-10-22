package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.repository.UserRepository;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieve(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll1(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> doSearch(String keyword) {
        return userRepository.doSearch(keyword);
    }

    @Override
    public Page<User> doSearch1(String keyword, Pageable pageable) {
        return userRepository.doSearch1(keyword, pageable);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
