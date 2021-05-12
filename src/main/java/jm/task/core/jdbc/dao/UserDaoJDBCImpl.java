package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.connect();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE Users(userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " userName varchar(45) not null ," +
                    " userLastName varchar(45) not null ," +
                    " userAge TINYINT(8) not null )  ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users(userName, userLastName, userAge)" +
                " VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE userId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("userName");
                String lastname = resultSet.getString("userLastName");
                int age = resultSet.getInt("userAge");
                User user = new User(name, lastname, (byte) age);
                user.setId((long) id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
