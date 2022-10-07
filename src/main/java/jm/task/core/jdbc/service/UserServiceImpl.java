package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dataBase;
    public UserServiceImpl() {
        dataBase = new UserDaoJDBCImpl();
    }
    public void createUsersTable() {
        dataBase.createUsersTable();
    }

    public void dropUsersTable() {
        dataBase.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dataBase.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        dataBase.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dataBase.getAllUsers();
    }

    public void cleanUsersTable() {
        dataBase.cleanUsersTable();
    }
}
