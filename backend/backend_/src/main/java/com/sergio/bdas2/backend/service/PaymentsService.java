package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.PaymentsDto;
import com.sergio.bdas2.backend.repository.PaymentsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsDao paymentsDao;
    private static final Logger logger = LoggerFactory.getLogger(PaymentsService.class);

    public List<PaymentsDto> getAllPayments() {
        return paymentsDao.getAllPayments();
    }

    public PaymentsDto getPaymentById(Long paymentId) {
        return paymentsDao.getPaymentById(paymentId);
    }

    public void updatePayment(Long id, PaymentsDto payment) {
        paymentsDao.updatePayment(id, payment);
    }

    public void addPayment(PaymentsDto payment) {
        logger.info("Adding new payment: {}", payment);
        paymentsDao.addPayment(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentsDao.deletePayment(paymentId);
    }

    // Add other methods as needed
}
