package org.example.authorizationservice.service;

import org.example.authorizationservice.model.User;
import org.example.authorizationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> getUserByEmailIfExist(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }
    public boolean isRegistered(String email) {
        Optional<User> checkingUser = Optional.ofNullable(userRepository.findByEmail(email));
        return checkingUser.isPresent();// Возвращаем существующего человека
    }
    public boolean addIfNotExists(User user) {
        Optional<User> checkingUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (checkingUser.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public boolean authAllowed(String email, String password) {
        Optional<User> checkingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if(checkingUser.isPresent()){
            if(password.equals(checkingUser.get().getPassword())){
                return true;
            }
        }
        return false;
    }



}
