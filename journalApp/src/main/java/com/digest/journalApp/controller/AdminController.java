package com.digest.journalApp.controller;

import com.digest.journalApp.cache.AppCache;
import com.digest.journalApp.entity.User;
import com.digest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService user;

    @Autowired
    AppCache appCache;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser(){
        List<User> all=user.findAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createuserAdmin(@RequestBody User users){
        user.saveAdmin(users);
    }

    @GetMapping("/clear-app-cache")
    public  void clearAppCache(){
        appCache.init();
    }
}
