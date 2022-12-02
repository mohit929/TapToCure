<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <link id="contextPathHolder" th:data-contextPath="@{/}"/>
<meta charset="UTF-8">
<title>TaptoCure/Payment</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<button id="rzp-button1"><h2>CLICK HERE FOR PAYMENT PROCESS</h2></button>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>

<script>
var options = {
    "key": "rzp_test_akArRL3ZRpmWSk", // Enter the Key ID generated from the Dashboard
    "amount": "Integer.parseInt(${amount})", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "TapToCure",
    "description": "Test Transaction",
    "image": "https://example.com/your_logo",
    "order_id": "${orderid}", //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    "handler": function (response){
        alert(response.razorpay_payment_id);
        alert(response.razorpay_order_id);
        alert(response.razorpay_signature); 
        alert("Payment Sucessfull"); 
        
     paymentUpdateOnServerSucess(response.razorpay_order_id ,"paid");
     
    },
    "prefill": {
        "name":"" ,
        "email": "",
        "contact": ""
    },
    "notes": {
        "address": "Razorpay Corporate Office"
    },
    "theme": {
        "color": "#FFA500"
    }
};
var rzp1 = new Razorpay(options);
rzp1.on('payment.failed', function (response){
        alert(response.error.code);
        alert(response.error.description);
        alert(response.error.source);
        alert(response.error.step);
        alert(response.error.reason);
        alert(response.error.metadata.order_id);
        alert(response.error.metadata.payment_id);
});
document.getElementById('rzp-button1').onclick = function(e){
    rzp1.open();
    e.preventDefault();
}



//
function paymentUpdateOnServerSucess(RazorOrderId,status){
fetch('http://localhost:8088/update_sucess_order', {
		  method: 'PUT', // or 'POST' or 'GET'
		  headers: {
		    'Content-Type': 'application/json',
		  },
		  body: JSON.stringify({
				RazorOrderId: RazorOrderId,
				status: status,
			}),
		})
		  .then((response) => response.json())
		  .then((data) => {
		    alert('Success:');
		  })
		  .catch((error) => {
		    console.error('Error:', error);
		  });
}




</script>
</body>
</html>