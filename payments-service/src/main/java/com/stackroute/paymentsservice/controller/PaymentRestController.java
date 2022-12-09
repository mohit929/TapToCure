package com.stackroute.paymentsservice.controller;
import com.stackroute.paymentsservice.service.PaymentService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.razorpay.Order;

@RequestMapping("/payment")
@RestController
public class PaymentRestController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PaymentService paymentService;
	

	//Getting Amount and ReceiptId for creating the payment order
	
	@GetMapping("/createOrderId/{amount}/{receptid}")
	public  ModelAndView createPaymentOrder(@PathVariable String amount ,@PathVariable String receptid, ModelMap model) {
		    String orderId=null;
		    ModelAndView mav =null;
		    ModelAndView mav1=null;
			String result=null;
	   if (Integer.parseInt(amount)>0 && Integer.parseInt(receptid)>0) {
		  

		    try {
		        int amt=Integer.parseInt(amount);

		      
		        Order order = paymentService.creatPaymen(amt, receptid);

		        orderId = order.get("id");
	           
		         model.addAttribute("orderid", orderId) ;
		         model.addAttribute("amount", String.valueOf(amt));
		       

			result=paymentService.SavePaymentDetails(order);


			         mav = new ModelAndView("checkout");

		    } catch (Exception e) {

		        System.out.println(e.getMessage());
		    }
		  
		
	   
	   
	   return mav;
	   }else {
		   model.addAttribute("errormsg", "Incorrct Input please give correct details i.e amount and Receiptid greater than Zero");
		   mav1=new ModelAndView("incorrect");
		   return mav1;
	   }
	}
		
	
	//After payment done status update is passed to this method to update the current status in database
	@PutMapping("/update_sucess_order")
	public String updatedSucessOrder(@RequestBody Map<String ,String> data){
		paymentService.updateSucessPayment(data);
		return "payment transferred succefully and update in database";
		
	}
	

	
		   
	   }
	   


