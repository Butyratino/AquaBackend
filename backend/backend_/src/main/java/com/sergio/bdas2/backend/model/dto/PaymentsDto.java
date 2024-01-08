package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsDto {
    private Long paymentId;
    private Long userId;
    private Double amount;
    private String date;
    private String method;
    private String cardNumber;
    private String bank;

    public static RowMapper<PaymentsDto> getPaymentsDtoMapper() {
        return (rs, rowNum) -> {
            PaymentsDto payment = new PaymentsDto();
            payment.setPaymentId(rs.getLong("PAYMENTID"));
            payment.setUserId(rs.getLong("USERID"));
            payment.setAmount(rs.getDouble("AMOUNT"));
            payment.setDate(rs.getString("DATE"));
            payment.setMethod(rs.getString("METHOD"));
            payment.setCardNumber(rs.getString("CARDNUMBER"));
            payment.setBank(rs.getString("BANK"));
            return payment;
        };
    }
}
