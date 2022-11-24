package com.stackroute.registrationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

        @Id
        @Field("_id")
        private String userId;
        @Field("email_id")
        private String emailId;
        @Field("user_name")
        private String userName;
        @Field("mobile_no")
        private String mobileNo;
        @Field("password")
        private String password;
        @Field("confirm_password")
        private String confirm_password;
        @Field("role")
        private Role role;
}
