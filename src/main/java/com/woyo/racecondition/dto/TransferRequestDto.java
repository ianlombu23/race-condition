package com.woyo.racecondition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private String customerId;
    private String accountNumber;
    private BigDecimal amountToTransfer;
    private String beneficiaryAccountNumber;
}
