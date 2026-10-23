package com.scaler.splitwisemorningjuly26.Services;

import com.scaler.splitwisemorningjuly26.DTO.Transaction;
import com.scaler.splitwisemorningjuly26.Models.*;
import com.scaler.splitwisemorningjuly26.Repositories.ExpenseRepository;
import com.scaler.splitwisemorningjuly26.Repositories.GroupRepository;
import com.scaler.splitwisemorningjuly26.Repositories.UserExpenseRepository;
import com.scaler.splitwisemorningjuly26.Repositories.UserRepository;
import com.scaler.splitwisemorningjuly26.Strategies.SettleUpStrategy;
import com.scaler.splitwisemorningjuly26.Strategies.SettleUpStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;
    public List<Transaction> settleUp(Long userId){
        // Fetch the user from userId and validate
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Fetch all the userExpense this user is part of
        List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(user);
        HashSet<Expense> uniqueExpenses = new HashSet<>();
        // Fetch all the expenses where this user was a part of
        for(UserExpense userExpense : userExpenses){
            uniqueExpenses.add(userExpense.getExpense());
        }
        // Calculate balance map from the expenses
        Map<Long, Double> balanceMap = new HashMap<>();
        for(Expense expense : uniqueExpenses){
            for(UserExpense ue : expense.getUserExpenses()){
                balanceMap.putIfAbsent(ue.getUser().getId(), 0.0);
                if(ue.getType().equals(UserExpenseType.PAID)){
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) + ue.getAmount());
                }else{
                    balanceMap.put(ue.getUser().getId(), balanceMap.get(ue.getUser().getId()) - ue.getAmount());
                }
            }
        }
        // Get txns - from settleUp strategy
        SettleUpStrategy strategy = SettleUpStrategyFactory.getSettleUpStrategy(SettleUpType.HEAP);
        List<Transaction> transactions = strategy.settleUp(balanceMap);
        // Filter out txns where this user is either on from or to

        List<Transaction> filteredTransactions = transactions
                .stream()
                .filter((transaction) -> transaction.getFromUser().equals(user.getId()) || transaction.getToUser().equals(user.getId()))
                .toList();

        return  filteredTransactions;
    }

    public List<Transaction> settleUpWithDummyGroup(Long userId){
        // Fetch the user from userId
        // Fetch all individual type groups(dummy) from groupRepository
        // Fetch all expenses of these groups
        // Calculate balance map from the expenses
        // Get txns - from settleUp strategy
        // Filter out txns where this user is either on from or to
        return null;
    }
}
