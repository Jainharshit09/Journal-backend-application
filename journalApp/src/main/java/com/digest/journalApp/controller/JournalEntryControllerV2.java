package com.digest.journalApp.controller;

import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService JournalEntryService;

    @GetMapping("/All")
    public List<JournalEntry> getAll(){
        return JournalEntryService.findAll();
    }

    @PostMapping("/Post")
    public JournalEntry createEntry(@RequestBody JournalEntry entry ){
        entry.setDate(LocalDateTime.now());
        JournalEntryService.saveEntry(entry);
        return entry ;
    }


    @GetMapping("/id/{myid}")
    public JournalEntry find(@PathVariable ObjectId myid){
        return  JournalEntryService.findById(myid).orElse(null);
    }


    @DeleteMapping("/id/{myid}")
    public void delete(@PathVariable ObjectId myid){
        JournalEntryService.deleteById(myid);
    }


    @PutMapping("/id/{myid}")
    public JournalEntry update(@PathVariable ObjectId myid, @RequestBody JournalEntry newentry) {
        JournalEntry old = JournalEntryService.findById(myid).orElse(null);
        if (old != null) {
            if (newentry.getTitle() != null && !newentry.getTitle().isEmpty()) {
                old.setTitle(newentry.getTitle());
            }
            if (newentry.getContent() != null && !newentry.getContent().isEmpty()) {
                old.setContent(newentry.getContent());
            }
        }
        JournalEntryService.saveEntry(old);
        return old;
    }
}