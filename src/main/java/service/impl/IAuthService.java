package service.impl;

import entity.Account;

public interface IAuthService {
    Account login(String username, String password);
    Account registerNewUser(String username, String password);
    boolean isUsernameAvailable(String username);
}
