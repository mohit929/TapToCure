package com.stackroute.paymentsservice.service;
import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import com.stackroute.paymentsservice.rabbitmqdto.Appointment;
import com.stackroute.paymentsservice.repositry.PaymentRepositry;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService  {

   @Autowired
    private PaymentRepositry paymentrepo;
    
	@Autowired
	private Environment env;
   
	
	private static PaymentDetailsPOJO p=null;
 

//Method for Creating the Order for Payment.
	@Override
	public Order creatPaymen(int amount, String receiptid) {
	 Order order=null;
	    try {
	      

	        RazorpayClient razorpay = new RazorpayClient(env.getProperty("rzp_key_id"), env.getProperty("rzp_key_secret"));
	        JSONObject orderRequest = new JSONObject();

	        orderRequest.put("amount", amount*100); // amount in the smallest currency
	        orderRequest.put("currency", env.getProperty("rzp_currency"));
	        orderRequest.put("receipt", receiptid);

	         order = razorpay.orders.create(orderRequest);

	       
	    } catch (RazorpayException e) {

	        System.out.println(e.getMessage());
	    }
	  
		return order;
	}
	
	
	
	
//Method for saving the Payment details in Database.
	   @Override
	    public String SavePaymentDetails(Order order) {
		  // p= new PaymentDetailsPOJO();
		//   p = paymentrepo.findByRazorOrderId(lastRecord.getRazorOrderId());

		   	p.setRazorOrderId(order.get("id"));
	        p.setAmount(order.get("amount"));
	        p.setCurrency(order.get("currency"));
	        p.setReceiptNumber(order.get("receipt"));
	        p.setStatus(order.get("status"));

	        paymentrepo.save(p);
	        return  "Payment is generated and save in database as well on Razor Server with amount INR"+p.getAmount()/100;
	    }


	   
	   
//Method for updating payment status in database post payment completed 	   
		@Override
		public void updateSucessPayment(PaymentDetailsPOJO data) {
			 
				
	 PaymentDetailsPOJO findByRazorOrderId =paymentrepo.findByRazorOrderId(data.getRazorOrderId());
		findByRazorOrderId.setStatus(data.getStatus());
		paymentrepo.save(findByRazorOrderId);
		  
			        
				
		}



//method for getting RabbitMQ message for patient details from Appointment Module

		static PaymentDetailsPOJO lastRecord= new PaymentDetailsPOJO();
		@Override
		public void getAppointment(Appointment appointment) {
			p=new PaymentDetailsPOJO();
	        p.setPatientId((appointment.getPatientDetails().getPatientId()));
	        p.setPatientName(appointment.getPatientDetails().getPatientName());
	        p.setPatientEmail(appointment.getPatientDetails().getPatientEmail());
			//lastRecord = paymentrepo.save(p);
		}
		


}
