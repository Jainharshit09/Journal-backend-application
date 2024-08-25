package com.digest.journalApp.controller;

import com.digest.journalApp.entity.User;
import com.digest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/public")
@RestController
public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @Autowired
    private UserService userService;
    @PostMapping("/UserPost")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        try {
            userService.saveNewEntry(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
