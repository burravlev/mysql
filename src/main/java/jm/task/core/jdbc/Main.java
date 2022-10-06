package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Daniil", "Buravev", (byte) 21);
        List<User> list = user.getAllUsers();
        System.out.println(list);
    }
}
