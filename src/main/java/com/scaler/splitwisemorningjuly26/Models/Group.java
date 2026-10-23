package com.scaler.splitwisemorningjuly26.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="splitwise_group")
public class Group extends BaseModel {
    private String name;
    private String description;
    // Group M:1 Admin
//    @ManyToOne
//    private User admin;
//    @ManyToMany(mappedBy = "groups")
//    private List<User> members;
    // Group 1:M Expense
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    @Enumerated(value= EnumType.STRING)
    private GroupType type;

}
// Boolean - wrong (isAdmin)
// Enum - correct - MemberType
