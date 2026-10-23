package com.scaler.splitwisemorningjuly26.Strategies;

import com.scaler.splitwisemorningjuly26.DTO.Transaction;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {
    List<Transaction> settleUp(Map<Long, Double> balanceMap);
}
