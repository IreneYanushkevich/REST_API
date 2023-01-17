package com.irinayanushkevich.restapi.service;

import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = new HibUserRepositoryImpl();

    public User save(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.create(user);
    }

    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    public User update(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return userRepository.update(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
