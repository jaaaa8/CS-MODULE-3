package service.impl;

public interface IAuthService {
    boolean login(String username, String password);
    boolean isUsernameAvailable(String username);
    boolean registerNewUser(String username, String password);
}
