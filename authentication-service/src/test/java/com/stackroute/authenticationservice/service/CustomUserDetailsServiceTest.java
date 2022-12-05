package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
public class CustomUserDetailsServiceTest {

    private User user,user1,saveUser;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setup()
    {
         user=new User(102,"mohit","7477008050","mohit929surya1c@gmail.com","doctor");
         user1=null;
         saveUser=new User();
         saveUser.setUsername("Gaurav Sinha");
         saveUser.setPassword("8770339014");
         saveUser.setRole("Patient");
         saveUser.setEmail("gauravsinha@gmail.com");
    }

    @Test
    public void testCustomUserDetailService() {
        Mockito.when(userRepository.findbyUserEmail("mohit929surya1c@gmail.com")).thenReturn(user);
        Assert.assertNotNull(customUserDetailsService.loadUserByUsername("mohit929surya1c@gmail.com"));
    }

    @Test
    public void testCustomUserDetailServiceExceptionPart()
    {
        Mockito.when((userRepository.findbyUserEmail("gauravsinha@gmail.com"))).thenReturn(user1);
        Assert.assertThrows(UsernameNotFoundException.class,()->customUserDetailsService.loadUserByUsername("auravsinha@gmail.com"));
    }

    @Test
    public void testSave()
    {
        Mockito.when(userRepository.save(saveUser)).thenReturn(saveUser);
        Assert.assertEquals(saveUser,userRepository.save(saveUser));
    }




}
