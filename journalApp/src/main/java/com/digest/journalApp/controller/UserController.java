package com.digest.journalApp.controller;

import com.digest.journalApp.entity.User;
import com.digest.journalApp.repository.UserRepo;
import com.digest.journalApp.service.UserService;
import com.digest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user_db = userService.findByUsername(username);
        user_db.setUsername(user.getUsername());
        user_db.setPassword(user.getPassword());
        userService.saveNewEntry(user_db);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<?> grettings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hii"+authentication.getName()+" Weather Fells Like"+weatherService.getWeather("Bhilwara").getCurrent(),HttpStatus.OK);

    }
}

