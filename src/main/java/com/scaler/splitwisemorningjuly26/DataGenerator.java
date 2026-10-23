package com.scaler.splitwisemorningjuly26;


import com.scaler.splitwisemorningjuly26.Models.*;
import com.scaler.splitwisemorningjuly26.Repositories.ExpenseRepository;
import com.scaler.splitwisemorningjuly26.Repositories.GroupRepository;
import com.scaler.splitwisemorningjuly26.Repositories.UserExpenseRepository;
import com.scaler.splitwisemorningjuly26.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataGenerator {
    @Autowired
    private UserRepository userRepositories;
    @Autowired
    private ExpenseRepository expenseRepositories;
    @Autowired
    private GroupRepository groupRepositories;
    @Autowired
    private UserExpenseRepository expenseUserRepositories;
    public void generateData(){
        User user1 = new User();
        user1.setName("Akash");
        user1.setEmail("akash.pal95@gmail.com");
        user1.setPassword("passpass");

        userRepositories.save(user1);

        User user2 = new User();
        user2.setName("Sam");
        user2.setEmail("sam.pal95@gmail.com");
        user2.setPassword("passpass");

        userRepositories.save(user2);

        User user3 = new User();
        user3.setName("Rajesh");
        user3.setEmail("rajesh.95@gmail.com");
        user3.setPassword("passpass");

        userRepositories.save(user3);

        User user4 = new User();
        user4.setName("Amrita");
        user4.setEmail("amrita.pal95@gmail.com");
        user4.setPassword("passpass");

        userRepositories.save(user4);


        //Creating a group
        Group group = new Group();
        group.setName("GoaTrip");
        List<Expense> expenses = new ArrayList<>();
        groupRepositories.save(group);

        //Creating an expense
        Expense ex1 = new Expense();
        ex1.setAmount(2000.0);
        ex1.setGroup(group);
        ex1.setDescription("Goa car rent");
        ex1.setCreatedBy(user1);
        ex1.setType(ExpenseType.NORMAL);
        expenseRepositories.save(ex1);

        //List to store all expenseUsers
        List<UserExpense> expenseUsers = new ArrayList<>();

        //Creating expense users
        UserExpense eu1 = new UserExpense();
        eu1.setAmount(2000.0);
        eu1.setUser(user1);
        eu1.setType(UserExpenseType.PAID);
        eu1.setExpense(ex1);

        expenseUserRepositories.save(eu1);

        UserExpense eu2 = new UserExpense();
        eu2.setAmount(500.0);
        eu2.setUser(user1);
        eu2.setType(UserExpenseType.HAD_TO_PAY);
        eu2.setExpense(ex1);

        expenseUserRepositories.save(eu2);

        UserExpense eu3 = new UserExpense();
        eu3.setAmount(500.0);
        eu3.setUser(user2);
        eu3.setType(UserExpenseType.HAD_TO_PAY);
        eu3.setExpense(ex1);

        expenseUserRepositories.save(eu3);

        UserExpense eu4 = new UserExpense();
        eu4.setAmount(500.0);
        eu4.setUser(user3);
        eu4.setType(UserExpenseType.HAD_TO_PAY);
        eu4.setExpense(ex1);

        expenseUserRepositories.save(eu4);

        UserExpense eu5 = new UserExpense();
        eu5.setAmount(500.0);
        eu5.setUser(user4);
        eu5.setType(UserExpenseType.HAD_TO_PAY);
        eu5.setExpense(ex1);

        expenseUserRepositories.save(eu5);

        expenseUsers.add(eu1);
        expenseUsers.add(eu2);
        expenseUsers.add(eu3);
        expenseUsers.add(eu4);
        expenseUsers.add(eu5);


        ex1.setUserExpenses(expenseUsers);

        expenseRepositories.save(ex1);

        expenses.add(ex1);

        group.setExpenses(expenses);

        groupRepositories.save(group);
        System.out.println("Checking");
    }

}
// Group - Goa Trip
// Exp1 - Car rent of 2000
// UExp1 : Akash: 2000
// UExp2 : Akash: -500, UExp3 : Sam: -500, UExp4: Rajesh: -500, UExp5: Amrita : -500