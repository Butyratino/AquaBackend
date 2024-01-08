package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.PaymentsDto;
import com.sergio.bdas2.backend.service.PaymentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/all")
    public List<PaymentsDto> getAllPayments() {
        List<PaymentsDto> payments = paymentsService.getAllPayments();
        return payments;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentsDto> getPaymentById(@PathVariable Long paymentId) {
        PaymentsDto payment = paymentsService.getPaymentById(paymentId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody PaymentsDto payment) {
        System.out.println("Received POST request to add a payment");
        paymentsService.addPayment(payment);
        return new ResponseEntity<>("Payment added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/update/{paymentId}")
    public ResponseEntity<Void> updatePayment(@PathVariable Long paymentId, @RequestBody PaymentsDto payment) {
        payment.setPaymentId(paymentId);
        paymentsService.updatePayment(paymentId, payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentsService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
