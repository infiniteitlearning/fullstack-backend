package com.traders.atlantic.controller;

import com.traders.atlantic.exception.UserNotFoundException;
import com.traders.atlantic.model.User;
import com.traders.atlantic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("http://fullstackreactcwa.s3-website-us-east-1.amazonaws.com")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("user/{id}")
    public User updateUser(@RequestBody User userUpdate, @PathVariable Long id) {
            return userRepository.findById(id)
                    .map(user -> {
                       user.setUsername(userUpdate.getUsername());
                       user.setName(userUpdate.getName());
                       user.setEmail(userUpdate.getEmail());
                       return userRepository.save(user);
                    }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw  new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id {} has been deleted " + id;
    }
}
