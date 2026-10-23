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
public class UserGroup extends BaseModel{
    // UserGroup M:1 User
    @ManyToOne
    private User user;
    // UserGroup M:1 Group
    @ManyToOne
    private Group group;
    @Enumerated(value=EnumType.STRING)
    private UserType type;
}