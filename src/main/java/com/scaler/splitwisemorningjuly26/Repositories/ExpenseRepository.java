package com.scaler.splitwisemorningjuly26.Repositories;

import com.scaler.splitwisemorningjuly26.Models.Expense;
import com.scaler.splitwisemorningjuly26.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findAllByGroup(Group group);
}
