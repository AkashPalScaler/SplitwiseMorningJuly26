package com.scaler.splitwisemorningjuly26.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Long fromUser;
    private Long toUser;
    private Double amount;
}
