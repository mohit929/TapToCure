package com.stackroute.paymentsservice.repositry;

import com.stackroute.paymentsservice.entity.PaymentDetailsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositry extends JpaRepository<PaymentDetailsPOJO,Long> {
}
