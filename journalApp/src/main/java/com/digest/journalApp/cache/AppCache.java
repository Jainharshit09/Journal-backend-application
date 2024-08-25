package com.digest.journalApp.cache;

import com.digest.journalApp.entity.ConfigJournalAppEntity;
import com.digest.journalApp.repository.configJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        weather_api;
    }

    @Autowired
    private configJournalAppRepository configJournalAppRepository;
    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();
        List<ConfigJournalAppEntity> all=configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity ConfigJournalAppEntity:all){
            appCache.put(ConfigJournalAppEntity.getKey(),ConfigJournalAppEntity.getValue());
        }
    }

}
