package com.stackroute.paymentsservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="paymentTable")
@Getter
@Setter
public class PaymentDetailsPOJO {
   @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long TapToCurePaymentId;
    private int patientId;
    private String patientName;
    private String patientEmail;
    private String RazorOrderId;
    private int  amount;
    private String currency;
    private String receiptNumber;
    private String status;

}
