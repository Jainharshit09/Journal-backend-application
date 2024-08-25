package com.digest.journalApp.service;

import com.digest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo;
    @Disabled
    @Test
    public void testfindByUsername(){
        assertEquals(5,2+3);
        assertNotNull(userRepo.findByUsername("harshit"));
    }
    @ParameterizedTest
    @CsvSource({
            "2,2,4",
            "5,2,7",
            "9,10,23"
    })
    public void test(int a,int b,int exp){
        assertEquals(exp,a+b);
    }

}
