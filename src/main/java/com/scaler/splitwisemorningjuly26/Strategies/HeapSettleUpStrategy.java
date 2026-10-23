package com.scaler.splitwisemorningjuly26.Strategies;

import com.scaler.splitwisemorningjuly26.DTO.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
class Pair implements Comparable<Pair> {
    @Override
    public int compareTo(Pair o) {
        return Double.compare(o.amount, this.amount);
    }

    Long userId;
    Double amount;
    public Pair(Long userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUp(Map<Long, Double> balanceMap) {
        // Divide into 2 buckets (paid by and had to pay)
        PriorityQueue<Pair> receivers = new PriorityQueue<>();
        PriorityQueue<Pair> senders = new PriorityQueue<>();
        // A_id:+1750, B_id:-500, C_id: -1250, D_id:-450
        for(Map.Entry<Long, Double> entry : balanceMap.entrySet()){
            if(entry.getValue() > 0){
                receivers.add(new Pair(entry.getKey(), entry.getValue()));
            }else{
                senders.add(new Pair(entry.getKey(), -1*entry.getValue()));
            }
        }
        List<Transaction> transactions = new ArrayList<>();
        while(!receivers.isEmpty() && !senders.isEmpty()){
            Pair maxReceive = receivers.poll(); // 1000
            Pair maxSend = senders.poll(); // 900

            if(maxReceive.getAmount() > maxSend.getAmount()){
                Transaction transaction = new Transaction();
                transaction.setFromUser(maxSend.getUserId());
                transaction.setToUser(maxReceive.getUserId());
                transaction.setAmount(maxSend.getAmount());
                transactions.add(transaction);

                receivers.add(new Pair(maxReceive.getUserId(), maxReceive.getAmount() - maxSend.getAmount()));
            }else if(maxReceive.getAmount() < maxSend.getAmount()){
                Transaction transaction = new Transaction();
                transaction.setFromUser(maxSend.getUserId());
                transaction.setToUser(maxReceive.getUserId());
                transaction.setAmount(maxReceive.getAmount());
                transactions.add(transaction);

                senders.add(new Pair(maxSend.getUserId(), maxSend.getAmount() - maxReceive.getAmount()));
            }else{
                transactions.add(new Transaction(maxSend.getUserId(), maxReceive.getUserId(), maxReceive.getAmount()));
            }
        }

        return transactions;
    }
}
