package com.jerome.accounts.mapper;

import com.jerome.accounts.dto.AccountDto;
import com.jerome.accounts.entity.Accounts;

public class AccountMapper {

    // Entity to DTO
    public static AccountDto toDto (Accounts accounts, AccountDto accountDto){
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    // DTO to Entity
    public static Accounts toEntity (AccountDto accountDto, Accounts accounts){
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
