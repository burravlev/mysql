package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.DatabaseMetaData;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private List<User> users = new ArrayList<>();
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), last_name VARCHAR(45), age INT, PRIMARY KEY (id));");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user;");
        } catch (SQLException ex) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO user(name, last_name, age) VALUES('%s', '%s', %d);", name, lastName, age));
        } catch (SQLException ex) {

        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("DELETE FROM user WHERE id=%d", id));
        } catch (SQLException ex) {

        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM user;");
            User user = null;
            while (result.next()) {
                long id = result.getInt(1);
                String name = result.getString(2);
                String lastName = result.getString(3);
                byte age = (byte) result.getInt("age");
                user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException ex) {

        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user;");
        } catch (SQLException ex) {
        }
    }
}
