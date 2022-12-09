package com.stackroute.paymentsservice;

import static org.assertj.core.api.Assertions.assertThat;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.repositry.PaymentRepositry;
import com.stackroute.paymentsservice.service.PaymentServiceImpl;

@SpringBootTest
class ServiceLayerTest {

	
@Autowired
private Environment env;
	
@Autowired
private PaymentRepositry paymentrepo;


@Autowired
private PaymentServiceImpl service;


@Test
void loadContext() {
	
	assertThat(service).isNotNull();
}








//for testing the save method for saving the payment details in database.
	@Test 
	public void savePaymentTableTest() {
		PaymentDetailsPOJO p = new PaymentDetailsPOJO();
		p.setAmount(2);
		p.setCurrency("INR");
		p.setPatientEmail("email");
		p.setPatientName("patinetName");
		p.setRazorOrderId("order_id");
		p.setReceiptNumber("receipt");
		p.setStatus("status");
		paymentrepo.save(p);
		assertThat(p.getAmount()).isEqualTo(2);
		assertThat(p.getCurrency()).isEqualTo("INR");
		assertThat(p.getPatientEmail()).isEqualTo("email");
		assertThat(p.getPatientName()).isEqualTo("patinetName");
		assertThat(p.getRazorOrderId()).isEqualTo("order_id");
		assertThat(p.getReceiptNumber()).isEqualTo("receipt");
		assertThat(p.getStatus()).isEqualTo("status");
	}
	
	
//for the testing Creating order for payment.	
	
	@Test  
	public  void orderCreateTest() {
		
		 try {
		   
		        RazorpayClient razorpay = new RazorpayClient(env.getProperty("rzp_key_id"), env.getProperty("rzp_key_secret"));
		        JSONObject orderRequest = new JSONObject();
		        orderRequest.put("amount", 1*100); // amount in the smallest currency
		        orderRequest.put("currency", env.getProperty("rzp_currency"));
		        orderRequest.put("receipt", "test");
		        Order order = razorpay.orders.create(orderRequest);
		        assertThat(orderRequest.get("amount")).isEqualTo(order.get("amount"));
		        assertThat(orderRequest.get("currency")).isEqualTo(order.get("currency"));
		        assertThat(orderRequest.get("receipt")).isEqualTo(order.get("receipt"));
		 } catch (RazorpayException e) {
		        e.getMessage();
		    } catch (JSONException e) {
			e.printStackTrace();
		}
	   
	}
	
	
//for the testing for updating payment status in database
	@Test
	public void updatePaymentStatusTest() {

		 try {
		   
		        RazorpayClient razorpay = new RazorpayClient(env.getProperty("rzp_key_id"), env.getProperty("rzp_key_secret"));
		        JSONObject orderRequest = new JSONObject();
		        orderRequest.put("amount", 1*100); // amount in the smallest currency
		        orderRequest.put("currency", env.getProperty("rzp_currency"));
		        orderRequest.put("receipt", "test");
		        Order order = razorpay.orders.create(orderRequest);
		        
		        PaymentDetailsPOJO p = new PaymentDetailsPOJO();
		        
	            p.setAmount(order.get("amount"));
	            p.setCurrency(order.get("currency"));
	            p.setRazorOrderId(order.get("id"));
	            p.setStatus(order.get("status"));
	            
	            paymentrepo.save(p);
		        
		       PaymentDetailsPOJO p1 = paymentrepo.findByRazorOrderId(order.get("id"));
		       
		        p1.setStatus("paid");
		        
		        paymentrepo.save(p1);
		        
		        assertThat(p1.getStatus()).isEqualTo("paid");
		
		 } catch (RazorpayException e) {
		        e.getMessage();
		    } catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
