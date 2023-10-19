package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.repository.UserRepository;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> doSearch(String keyword) {
        return userRepository.doSearch(keyword);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
