package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.DatabaseMetaData;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private List<User> users = new ArrayList<>();
    private final String URL = "jdbc:mysql://localhost:3306/users";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = con.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), last_name VARCHAR(45), age INT, PRIMARY KEY (id));");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = (DriverManager.getConnection(URL, USERNAME, PASSWORD)).createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user;");
        } catch (SQLException ex) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = con.createStatement()) {
            statement.execute(String.format("INSERT INTO user(name, last_name, age) VALUES('%s', '%s', %d);", name, lastName, age));
        } catch (SQLException ex) {

        }
    }

    public void removeUserById(long id) {
        try (Statement statement = (DriverManager.getConnection(URL, USERNAME, PASSWORD)).createStatement()) {
            statement.execute(String.format("DELETE FROM user WHERE id=%d", id));
        } catch (SQLException ex) {

        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = (DriverManager.getConnection(URL, USERNAME, PASSWORD)).createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM user;");
            while (result.next()) {
                long id = result.getInt(1);
                String name = result.getString(2);
                String lastName = result.getString(3);
                byte age = (byte) result.getInt("age");
                users.add(new User(name, lastName, age));
            }

        } catch (SQLException ex) {

        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = (DriverManager.getConnection(URL, USERNAME, PASSWORD)).createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user;");
        } catch (SQLException ex) {
        }
    }
}
