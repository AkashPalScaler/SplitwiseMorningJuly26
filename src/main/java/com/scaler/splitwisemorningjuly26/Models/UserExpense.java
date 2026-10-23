package com.scaler.splitwisemorningjuly26.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExpense extends BaseModel {
    // UserExp M:1 User
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    @Enumerated(value= EnumType.STRING)
    private UserExpenseType type;
    private Double amount;
}
