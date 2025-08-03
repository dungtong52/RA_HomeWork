package com.ra.model;

import com.ra.model.enumClass.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    private long customerId;
    private long accountId;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private GenderEnum gender;
}
