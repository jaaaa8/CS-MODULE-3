package service;

import entity.Account;
import service.impl.IAccountService;
import service.impl.IAuthService;

public class AuthService implements IAuthService {
    private final IAccountService accountService = new AccountService();

    @Override
    public boolean login(String username, String password) {
        if(accountService.findAccountByUsername(username) == null) {
            return false;
        } else return accountService.findAccountByUsername(username).getPassword().equals(password);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return accountService.findAccountByUsername(username) == null;
    }

    @Override
    public boolean registerNewUser(String username, String password) {
        Account newAccount = new Account(username, password);
        return accountService.addNewAccount(newAccount);
    }
}
