package com.digest.journalApp.service;

import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.entity.User;
import com.digest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class
JournalEntryService {
    @Autowired
    private JournalEntryRepo JournalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUsername(username);
        JournalEntry save = JournalEntryRepository.save(journalEntry);
        user.getJournalEntry().add(save);
        userService.saveEntry(user);
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
    public void deleteById(ObjectId id, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntry().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        JournalEntryRepository.deleteById(id);
    }
}
