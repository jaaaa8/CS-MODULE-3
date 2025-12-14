package service.impl;

public interface IRegisterService {
    boolean isUsernameAvailable(String username);
    boolean registerNewUser(String username, String password, String email, String role);
}
