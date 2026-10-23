package com.scaler.splitwisemorningjuly26.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SettleUpUserResponseDTO {
    private List<Transaction> transactions;
    private String message;
    private ResponseStatus status;
}
