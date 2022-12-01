package com.stackroute.appointmentservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentServiceApplicationTests {

	private MockMvc mockMvc;
	private WebApplicationContext webApplicationContext;

	@Test
	void contextLoads()
	{

	}

}
