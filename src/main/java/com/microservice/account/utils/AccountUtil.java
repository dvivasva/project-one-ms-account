package com.microservice.account.utils;

import com.microservice.account.dto.AccountDto;
import com.microservice.account.model.Account;
import org.springframework.beans.BeanUtils;

public class AccountUtil {

    public static AccountDto entityToDto(Account account){
        var AccountDto=new AccountDto();
        BeanUtils.copyProperties(account,AccountDto);
        return AccountDto;
    }
    public static Account dtoToEntity(AccountDto accountDto){
        var entity=new Account();
        BeanUtils.copyProperties(accountDto,entity);
        return entity;
    }

}
