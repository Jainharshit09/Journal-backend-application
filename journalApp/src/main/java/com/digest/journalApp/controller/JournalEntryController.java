//package com.digest.journalApp.controller;
//
//import com.digest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//    private HashMap<Long,JournalEntry> map=new HashMap<>();
//    @GetMapping("/getAll")
//    public ArrayList<JournalEntry> getAll(){
//        return new ArrayList<>(map.values());
//    }
//
//    @PostMapping("/getPost")
//    public void createEntry(@RequestBody JournalEntry entry ){
//        map.put(entry.getId(), entry);
//    }
//    @GetMapping("/getid/{myid}")
//    public JournalEntry find(@PathVariable Long myid){
//        return map.get(myid);
//    }
//    @DeleteMapping("/getid/{myid}")
//    public boolean delete(@PathVariable Long myid){
//        if(map.containsKey(myid)) {map.remove(myid);
//        return true;}
//        return false;
//    }
//    @PutMapping("/getid/{myid}")
//    public JournalEntry update(@PathVariable Long id,@RequestBody JournalEntry entry){
//        return  map.put(id,entry);
//    }
//}
