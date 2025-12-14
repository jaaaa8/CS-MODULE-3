package service.impl;

import dto.AccountDto;
import entity.Account;

import java.util.List;

public interface IAccountService {
    List<AccountDto> getAllAccounts();
    boolean addNewAccount(Account account);
    boolean updatePassword(int accountId, String newPassword);
    boolean deleteAccount(int accountId);
    Account findAccountByUsername(String username);
}
