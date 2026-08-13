package com.prasanth.financemanager.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransacationRequest {
    @NotNull(message = "Amount is required")
    @Positive(message = "It should be Positive")
    private BigDecimal amount;
}
