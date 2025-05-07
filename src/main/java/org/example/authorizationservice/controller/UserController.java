package org.example.authorizationservice.controller;

import org.example.authorizationservice.model.User;
import org.example.authorizationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ApplicationContext applicationContext;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, ApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String email,@RequestParam String password){
        Optional<User> checkingUser = userService.getUserByEmailIfExist(email);
        if(checkingUser.isEmpty()){
            User user = applicationContext.getBean("user", User.class);
            user.setEmail(email);
            user.setPassword(password);
            if(userService.addIfNotExists(user)){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }
    @PostMapping("/auth")
    public ResponseEntity<User> authUser(@RequestParam String email,@RequestParam String password){
        Optional<User> checkingUser = userService.getUserByEmailIfExist(email);
        if(checkingUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            if(checkingUser.get().getPassword().equals(password)){
                return ResponseEntity.status(HttpStatus.OK).build();//TODO: добавить создание токена JWT и выдачу
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
    }




}
