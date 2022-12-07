package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.entity.Role;
import com.stackroute.registrationservice.entity.User;
import com.stackroute.registrationservice.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterationServiceTest {

    @Autowired
    private RegistrationServices registrationServices;
    @MockBean
    private UserRepo userRepo;

    @BeforeEach
    void setUp()
    {
        Optional<User> user=Optional.of(new User("123","poojamiskin8@gmail.com","Pooja","9343205920","Pooja@1234","Pooja@1234", Role.Doctor));
        Mockito.when(userRepo.findByEmailId("poojamiskin8@gmail.com")).thenReturn(null);
    }
    @Test
    public void testGetUserById()
    {

        User user=registrationServices.getByemailId("poojamiskin8@gmail.com");
        assertEquals("123",user.getUserId());

    }


}