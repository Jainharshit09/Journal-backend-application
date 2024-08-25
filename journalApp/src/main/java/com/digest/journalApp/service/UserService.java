package com.digest.journalApp.service;

import com.digest.journalApp.entity.User;
import com.digest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public void saveNewEntry(User users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("Users"));
        userRepo.save(users);
    }
    public void saveuser(User user){
        userRepo.save(user);
    }

    public void saveAdmin(User users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("Admin"));
        userRepo.save(users);
    }
    
    public List<User> findAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}

