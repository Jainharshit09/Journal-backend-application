package com.digest.journalApp.service;

import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.entity.User;
import com.digest.journalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

@Slf4j
public class
JournalEntryService {

    @Autowired
    private JournalEntryRepo JournalEntryRepository;

    @Autowired
    private UserService userService;

   private static final Logger Logger= LoggerFactory.getLogger(JournalEntryService.class);
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUsername(username);
        JournalEntry save = JournalEntryRepository.save(journalEntry);
        user.getJournalEntry().add(save);
        userService.saveuser(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        JournalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll(){
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String username){
        try{
            User user = userService.findByUsername(username);
            boolean b = user.getJournalEntry().removeIf(x -> x.getId().equals(id));
            if(b){
                userService.saveuser(user);
                JournalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            log.error("Error");
            throw new RuntimeException("An error is occured");
        }
    }
}
