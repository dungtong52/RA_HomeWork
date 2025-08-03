package com.ra.model;

import com.ra.model.enumClass.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    private long accountId;
    private String username;
    private String password;
    private RoleEnum role;
    private boolean status;
}
