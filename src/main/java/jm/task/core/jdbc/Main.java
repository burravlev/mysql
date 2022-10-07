package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl users = new UserServiceImpl();
        users.createUsersTable();
        users.saveUser("Daniil", "Buravlev", (byte) 21);
        users.saveUser("Marat", "Ivanov", (byte) 50);
        users.saveUser("Marina", "Semenova", (byte) 40);
        users.saveUser("Alina", "Mennikova", (byte) 20);
        System.out.println(users.getAllUsers());
    }
}
