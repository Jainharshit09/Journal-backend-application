package com.digest.journalApp.controller;

import com.digest.journalApp.entity.User;
import com.digest.journalApp.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> getAllUsers(){
       return userService.findAll();
    }

    @PostMapping("/UserPost")
    public ResponseEntity<?> createUser(@Validated @RequestBody User user) {
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        User user_db = userService.findByUsername(username);

        if (user_db != null) {
            user_db.setUsername(user.getUsername());
            user_db.setPassword(user.getPassword());
            userService.saveEntry(user_db);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User with username '" + username + "' not found.", HttpStatus.NOT_FOUND);
        }
    }

}
