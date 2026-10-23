package com.scaler.splitwisemorningjuly26.Repositories;

import com.scaler.splitwisemorningjuly26.Models.Expense;
import com.scaler.splitwisemorningjuly26.Models.User;
import com.scaler.splitwisemorningjuly26.Models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense,Long> {
    List<UserExpense> findAllByUser(User user);

    List<UserExpense> findAllByExpense(Expense expense);
}
