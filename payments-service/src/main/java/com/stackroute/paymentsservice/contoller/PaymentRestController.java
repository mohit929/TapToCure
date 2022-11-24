package com.stackroute.paymentsservice.contoller;

import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.repositry.PaymentRepositry;
import com.stackroute.paymentsservice.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class PaymentRestController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@GetMapping("/createOrderId/{amount}/{reciptid}")
	public String createPaymentOrder(@PathVariable String amount ,@PathVariable String reciptid, ModelMap model) {
	    String orderId=null;
	    ModelAndView mav =null;
		String result=null;

	    try {
	        int amt=Integer.parseInt(amount);

	        RazorpayClient razorpay = new RazorpayClient(env.getProperty("rzp_key_id"), env.getProperty("rzp_key_secret"));
	        JSONObject orderRequest = new JSONObject();

	        orderRequest.put("amount", amt*100); // amount in the smallest currency
	        orderRequest.put("currency", env.getProperty("rzp_currency"));
	        orderRequest.put("receipt", reciptid);

	        Order order = razorpay.orders.create(orderRequest);

	        orderId = order.get("id");

	         model.addAttribute("orderid", orderId) ;

		result=paymentService.SavePaymentDetails(order);


		         mav = new ModelAndView("checkout");

	    } catch (RazorpayException e) {

	        System.out.println(e.getMessage());
	    }
	    return result;
	}


}
