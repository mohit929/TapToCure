package com.stackroute.registrationservice.service;

import com.stackroute.registrationservice.entity.User;

import java.util.List;

public interface RegistrationServices
{

    public String createUser(User user);
    public boolean validate(User user);
    public List<User> getUser();
    public User getByemailId(String email_id);
}
