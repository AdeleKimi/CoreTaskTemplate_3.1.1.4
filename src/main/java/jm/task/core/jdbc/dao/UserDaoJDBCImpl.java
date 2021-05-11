package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("CREATE TABLE Users(userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " userName varchar(45) not null ," +
                    " userLastNme varchar(45) not null ," +
                    " userAge TINYINT(8) not null )  ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("DROP TABLE users");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
//        System.out.println(String.format ("INSERT INTO users( userName, userLastNme, userAge)" +
//                " VALUES ('%s','%s',%d)",name,lastName,age));
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute(String.format ("INSERT INTO users( userName, userLastNme, userAge)" +
                    " VALUES (\"%s\",\"%s\",%d)",name,lastName,age));

        }catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute(String.format("DELETE FROM users WHERE userId = %d",id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.connect().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString("userName");
                String lastname = resultSet.getString("userLastNme");
                int age = resultSet.getInt("userAge");
                User user = new User(name,lastname,(byte)age);
                user.setId((long) id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.execute("TRUNCATE TABLE users");

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
