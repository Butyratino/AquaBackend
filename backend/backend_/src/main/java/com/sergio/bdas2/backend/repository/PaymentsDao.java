package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.PaymentsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentsDao {

    private final JdbcTemplate jdbcTemplate;

    public void addPayment(PaymentsDto payment) {
        String query = "INSERT INTO PAYMENTS (USERID, AMOUNT, DATE, METHOD, CARDNUMBER, BANK) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                payment.getUserId(),
                payment.getAmount(),
                payment.getDate(),
                payment.getMethod(),
                payment.getCardNumber(),
                payment.getBank()
        );
    }

    public List<PaymentsDto> getAllPayments() {
        String query = "SELECT * FROM PAYMENTS ORDER BY PAYMENTID";
        return jdbcTemplate.query(query, PaymentsDto.getPaymentsDtoMapper());
    }

    public PaymentsDto getPaymentById(Long id) {
        String query = "SELECT * FROM PAYMENTS WHERE PAYMENTID = ?";
        List<PaymentsDto> foundPayments = jdbcTemplate.query(query, new Object[]{id}, PaymentsDto.getPaymentsDtoMapper());
        if (foundPayments.size() != 1) {
            throw new DaoException("Payment with ID " + id + " not found or not unique");
        }
        return foundPayments.get(0);
    }

    public void updatePayment(Long id, PaymentsDto payment) {
        String sql = "UPDATE PAYMENTS SET USERID = ?, AMOUNT = ?, DATE = ?, METHOD = ?, CARDNUMBER = ?, BANK = ? WHERE PAYMENTID = ?";
        jdbcTemplate.update(sql, payment.getUserId(), payment.getAmount(), payment.getDate(), payment.getMethod(), payment.getCardNumber(), payment.getBank(), id);
    }

    public void deletePayment(Long paymentId) {
        String query = "DELETE FROM PAYMENTS WHERE PAYMENTID = ?";
        jdbcTemplate.update(query, paymentId);
    }

    // Add other CRUD operations as needed
}
