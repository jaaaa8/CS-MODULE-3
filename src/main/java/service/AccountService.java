package service;

import dto.AccountDto;
import entity.Account;
import repository.AccountRepository;
import repository.impl.IAccountRepository;
import service.impl.IAccountService;

import java.util.List;

public class AccountService implements IAccountService {
    private final IAccountRepository accountRepository = new AccountRepository();

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public boolean addNewAccount(Account account) {
        System.out.println("ACCOUNT SERVICE ADD CALLED");
        return accountRepository.addNewAccount(account);
    }

    @Override
    public boolean updatePassword(int accountId, String newPassword) {
        return accountRepository.updatePassword(accountId, newPassword);
    }

    @Override
    public boolean deleteAccount(int accountId) {
        return accountRepository.deleteAccount(accountId);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }
}
