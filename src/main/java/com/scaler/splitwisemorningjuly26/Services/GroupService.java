package com.scaler.splitwisemorningjuly26.Services;

import com.scaler.splitwisemorningjuly26.DTO.Transaction;
import com.scaler.splitwisemorningjuly26.Models.*;
import com.scaler.splitwisemorningjuly26.Repositories.ExpenseRepository;
import com.scaler.splitwisemorningjuly26.Repositories.GroupRepository;
import com.scaler.splitwisemorningjuly26.Repositories.UserExpenseRepository;
import com.scaler.splitwisemorningjuly26.Strategies.SettleUpStrategy;
import com.scaler.splitwisemorningjuly26.Strategies.SettleUpStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;

    public List<Transaction> settleUpGroup(Long groupId){
        // Fetch the group from group Id and validate
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new IllegalArgumentException("Group not found"));

        // Fetch all the expenses of the group
        List<Expense> expenses = expenseRepository.findAllByGroup(group);
        // Fetch all the UserExpense of the all the above expenses
        // We need to create the balanceMap
        Map<Long, Double> balanceMap = new HashMap<>();
        for(Expense expense : expenses){
            List<UserExpense> userExpenses = userExpenseRepository.findAllByExpense(expense);
            for(UserExpense ue : userExpenses){
                balanceMap.putIfAbsent(ue.getUser().getId(), 0.0);
                if(ue.getType().equals(UserExpenseType.PAID)){
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) + ue.getAmount());
                }else{
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) - ue.getAmount());
                }
            }
        }

        // Strategy - Get SettleUpAlgorithm for calculating the txns
        SettleUpStrategy strategy = SettleUpStrategyFactory.getSettleUpStrategy(SettleUpType.HEAP);
        // Return the transaction
        return strategy.settleUp(balanceMap);
    }
}
