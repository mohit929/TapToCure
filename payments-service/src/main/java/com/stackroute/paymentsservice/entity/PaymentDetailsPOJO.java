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


  public int getPatientId() {
    return patientId;
  }

  public void setPatientId(int patientId) {
    this.patientId = patientId;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientEmail() {
    return patientEmail;
  }

  public void setPatientEmail(String patientEmail) {
    this.patientEmail = patientEmail;
  }

  public String getRazorOrderId() {
    return RazorOrderId;
  }

  public void setRazorOrderId(String razorOrderId) {
    RazorOrderId = razorOrderId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getReceiptNumber() {
    return receiptNumber;
  }

  public void setReceiptNumber(String receiptNumber) {
    this.receiptNumber = receiptNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
