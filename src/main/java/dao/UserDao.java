package dao;

import domain.User;

public interface UserDao {

    boolean userExists(User user);

    int checkAuth(User user);

    boolean addUser(User user);
}
