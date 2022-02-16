package com.microservice.account.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountTransfer {
    private int typeOperation; // 0=deposit or 1=retirement
    private double amount;
}
