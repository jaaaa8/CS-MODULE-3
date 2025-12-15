package service;

import entity.Account;
import repository.AccountRepository;
import repository.impl.IAccountRepository;
import service.impl.IAccountService;
import service.impl.IAuthService;

public class AuthService implements IAuthService {
    private final IAccountRepository accountRepository = new AccountRepository();

    @Override
    public Account login(String username, String password) {
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null){
            return null;
        }else if(account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return accountRepository.findAccountByUsername(username) == null;
    }

    @Override
    public Account registerNewUser(String username, String password) {
        Account newAccount = new Account(username, password);
        boolean isAdded = accountRepository.addNewAccount(newAccount);
        if(isAdded){
            System.out.println("REGISTER SERVICE CALLED");
            return newAccount;
        }
        return null;
    }
}
