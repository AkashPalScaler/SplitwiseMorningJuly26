package com.scaler.splitwisemorningjuly26.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private String description;
    private Double amount;
    // Expense M:1 User
    @ManyToOne
    private User createdBy;
    @Enumerated(value= EnumType.STRING)
    private ExpenseType type;
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> userExpenses;
    @ManyToOne
    private Group group;

}
// Exp1 : A:500 (PAID), B:500(PAID) ,
//        A:250(HAD_TO_PAY), B:250(HAD_TO_PAY), C:250(HAD_TO_PAY), D:250(HAD_TO_PAY)


// Exp2(Txn) : A:300(PAID), B:300(HAD_TO_PAY) | B -> A (300)