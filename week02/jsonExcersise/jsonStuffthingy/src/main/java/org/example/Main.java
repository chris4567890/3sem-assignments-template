package org.example;

import Entities.AccountMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AccountMapper accountMapper = new AccountMapper();
        ArrayList<AccountDTO> accountDTOS = (ArrayList<AccountDTO>) accountMapper.getListofAccountDtos();
        for(AccountDTO accountDTO : accountDTOS){
            System.out.println(accountDTO.toString());
        }


    }
}