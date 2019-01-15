package dao.impls;

import java.util.*;

import dao.UserDao;
import domain.User;

public class InMemoryUserDao implements UserDao {

    private List<User> users = Collections.synchronizedList(new LinkedList<User>());

    @Override
    public boolean userExists(User user) {
        return users.contains(user);
    }

    @Override
    public int checkAuth(User user) {
        for (User userInList : users){

            String userLogin = user.getLogin();
            String userPassword = user.getPassword();

            if (userInList.getLogin().equals(userLogin) && userInList.getPassword().equals(userPassword))
                return 0;
            if (userInList.getLogin().equals(user.getLogin()) && !userInList.getPassword().equals(userPassword))
                return 2;
        }

        return 1;
    }

    @Override
    public boolean addUser(User user) {
        if (userExists(user))
            return false;

        return users.add(user);
    }
}
