package service;

import domain.User;

public interface UserService {

    int checkAuth(User user);

    boolean register(User user);

    boolean checkUserExists(User user);
}
