package service;

import service.impl.IAccountService;
import service.impl.ILogin;

public class LoginService implements ILogin {
    private final IAccountService accountService = new AccountService();

    @Override
    public boolean login(String username, String password) {
        if(accountService.findAccountByUsername(username) == null) {
            return false;
        } else return accountService.findAccountByUsername(username).getPassword().equals(password);
    }
}
