package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    User expectedUser,saveUser;

    @BeforeEach
    public void setup()
    {
        expectedUser = new User();
        expectedUser.setId(101);
        expectedUser.setUsername("mohit");
        expectedUser.setRole("doctor");
        expectedUser.setPassword("7477008050");
        expectedUser.setEmail("mohit929surya1c@gmail.com");
    }

    @Test
    public void testFindbyUserEmail()
    {
        User user = userRepository.findbyUserEmail("mohit929surya1c@gmail.com");
        Assert.assertEquals(expectedUser, user);
    }

    @Test
    public void testSave()
    {   saveUser=new User();
        saveUser.setEmail("gauravsinha@gmail.com");
        saveUser.setPassword("8770339014");
        saveUser.setRole("Patient");
        saveUser.setUsername("Gaurav Sinha");
        Assert.assertEquals(saveUser,userRepository.save(saveUser));
        userRepository.delete(saveUser);
    }

}
