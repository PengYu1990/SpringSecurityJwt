package com.hugo.jwt.auth.service;

import com.hugo.jwt.auth.domain.User;
import com.hugo.jwt.auth.repo.UserRepository;
import com.hugo.jwt.auth.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean exist(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
