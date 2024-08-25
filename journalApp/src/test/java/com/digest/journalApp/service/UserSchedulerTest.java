package com.digest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import scheduler.UserScheduler;

@SpringBootTest
public class UserSchedulerTest {
    @Autowired
    public UserScheduler  userScheduler;
    @Test
    public void testfetch(){
        userScheduler.sendWeeklySentimentEmail();
    }
}
