package service.impls;

import dao.UserDao;
import dao.impls.InMemoryUserDao;
import domain.User;
import service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new InMemoryUserDao();

    @Override
    public boolean checkUserExists(User user) {
        return userDao.userExists(user);
    }

    @Override
    public int checkAuth(User user) {
        return userDao.checkAuth(user);
    }

    @Override
    public boolean register(User user) {
        return userDao.addUser(user);
    }
}
