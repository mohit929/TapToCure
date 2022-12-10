package com.stackroute.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.authenticationservice.exception.InvalidCredentialsException;
import com.stackroute.authenticationservice.model.AuthenticationRequest;
import com.stackroute.authenticationservice.model.AuthenticationResponse;
import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

@SpringBootTest
public class AuthenticationControllerTest {

    MockMvc mockMvc;

    @Autowired
    AuthenticationController authenticationController;

    @BeforeEach
    public void setup()
    {
        mockMvc= MockMvcBuilders.standaloneSetup(authenticationController).build();
    }


    @Test
    public void testAuthenticate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/generateToken").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("deepak.suryawanshi@gmail.com", "Deepak@123"))))
                .andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

  @Test
  public void testAuthenticateExceptionPart() throws Exception
  {
      Assert.assertThrows(NestedServletException.class,()->{
          mockMvc.perform(MockMvcRequestBuilders.post("/generateToken").contentType(MediaType.APPLICATION_JSON)
                          .content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("mohit930surya1c@gmail.com", "7477008050"))))
                  .andReturn();
      });

  }

  @Test
    public void testingAgain() throws JsonProcessingException,Exception
  {
      try{
          mockMvc.perform(MockMvcRequestBuilders.post("/generateToken").contentType(MediaType.APPLICATION_JSON)
                          .content(new ObjectMapper().writeValueAsString(new AuthenticationRequest("mohit930surya1c@gmail.com", "7477008050"))))
                  .andReturn();
      }
      catch(NestedServletException exception)
      {  System.out.println(exception.getCause() instanceof InvalidCredentialsException);
          Assert.assertEquals("Credentials are not correct.Kindly try with another credentials",exception.getCause().getMessage());
      }

  }



}
