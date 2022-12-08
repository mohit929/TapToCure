package com.stackroute.registrationservice.repo;

import com.stackroute.registrationservice.entity.Role;
import com.stackroute.registrationservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepoTest {

        @Autowired
        private UserRepo repo;

        private User user;

        List<User> userList;

        @BeforeEach
        public void init() {
            userList = new ArrayList<>();
            userList.add(new User("123", "poojamiskin9@gmail.com", "puja", "9342984203", "Pooja@123", "Pooja@123", Role.Doctor));
            userList.add(new User("124", "poojamiskin8@gmail.com", "Pooja", "9342984207", "Pooja@123", "Pooja@123", Role.Doctor));
            user=new User("124", "poojamiskin8@gmail.com", "Pooja", "9342984207", "Pooja@123", "Pooja@123", Role.Doctor);

        }


        @Test
        public void saveUser_Test(){
            //pass
            repo.save(user);
            User result =repo.findById("124").get();
            assertEquals(user.getUserId(),result.getUserId());
        }

        @Test
        public void getUserById_Test() {
            repo.save(user);
            User result=repo.findById(user.getUserId()).get();
            assertEquals("124", result.getUserId());

        }

        @Test
        public void getByEmailId_Test(){
            //pass
            repo.saveAll(userList);
            User result=repo.findByEmailId(user.getEmailId()).get(0);
            assertEquals("poojamiskin8@gmail.com",result.getEmailId());
        }



}
