package com.digest.journalApp.controller;

import com.digest.journalApp.entity.JournalEntry;
import com.digest.journalApp.entity.User;
import com.digest.journalApp.service.JournalEntryService;
import com.digest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    JournalEntryService JournalEntryService;
    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity <?>  getAllJournalEntryOfUsers(@PathVariable String username){
        User user = userService.findByUsername(username);
        List<JournalEntry>all= user.getJournalEntry();
     if(all!=null && !all.isEmpty()){
         return new ResponseEntity<>(all,HttpStatus.OK);
     }
     else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }

    @PostMapping("/{username}")
    public ResponseEntity <JournalEntry> createEntry(@RequestBody JournalEntry entry ,@PathVariable String username ){
        try {
            entry.setDate(LocalDateTime.now());
            JournalEntryService.saveEntry(entry,username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/id/{myid}")
    public ResponseEntity <JournalEntry> find(@PathVariable ObjectId myid){
      Optional<JournalEntry> journalEntry=JournalEntryService.findById(myid);
      if(journalEntry.isPresent()){
          return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }


    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> delete(@PathVariable ObjectId myid ,@PathVariable String username) {
        try {

            JournalEntryService.deleteById(myid,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/id/{username}/{myid}")
    public ResponseEntity<?> update(@PathVariable ObjectId myid, @RequestBody JournalEntry newentry,@PathVariable String username) {
        JournalEntry old = JournalEntryService.findById(myid).orElse(null);
        if (old != null) {
            if (newentry.getTitle() != null && !newentry.getTitle().isEmpty()) {
                old.setTitle(newentry.getTitle());
            }
            if (newentry.getContent() != null && !newentry.getContent().isEmpty()) {
                old.setContent(newentry.getContent());
            }
            JournalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
      else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}