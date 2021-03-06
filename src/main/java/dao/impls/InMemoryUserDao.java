package dao.impls;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import dao.UserDao;
import domain.User;

public class InMemoryUserDao implements UserDao {

    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public boolean userExists(User user) {
        return users.containsKey(user.getLogin());
    }

    @Override
    public int checkAuth(User user) {
        if (!userExists(user))
            return 1;

        try {
            User userInMap = users.get(user.getLogin());

            if (userInMap.equals(user))
                return 0;

            if (!userInMap.getPassword().equals(user.getPassword()))
                return 2;
        }
        catch (Exception e){
            return 3;
        }

        return 3;
    }

    @Override
    public boolean addUser(User user) {
        if (userExists(user))
            return false;

        users.put(user.getLogin(), user);

        return true;
    }
}
