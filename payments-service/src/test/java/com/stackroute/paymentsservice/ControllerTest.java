package com.stackroute.paymentsservice;

import static org.assertj.core.api.Assertions.assertThat;import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.paymentsservice.controller.PaymentRestController;
import com.stackroute.paymentsservice.service.PaymentService;

@SpringBootTest(webEnvironment =WebEnvironment.RANDOM_PORT )
public class ControllerTest {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	PaymentRestController controller;
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate resttemplate;
	
	@Test
	void loadContext() {
		
		assertThat(controller).isNotNull();
	}
	
	
	@Test
	public void createPaymenntTest() {
	assertThat(this.resttemplate.getForObject("http://localhost:"+port+"/", String.class).contains("paymentsucessfull"));
	
	}
	
	
	
	@Test
	public void updatePaymenntTest() {
			
	assertThat(this.resttemplate.getForObject("http://localhost:"+port+"/", String.class).contains("Hellow"));
	
	}
	


}
