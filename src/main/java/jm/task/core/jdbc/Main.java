package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        User user = new User("Kolya", "Petrenko", (byte) 20);
        User user1 = new User("Sasha", "Petrenko", (byte) 25);
        User user2 = new User("Vova", "Petrenko", (byte) 30);
        User user3 = new User("galya", "Petrenko", (byte) 35);
        userDao.saveUser(user.getName(),user.getLastName(), user.getAge());
        System.out.println("User  именем - " + user.getName() + " добавлен в базу данных");

        userDao.saveUser(user1.getName(),user1.getLastName(), user1.getAge());
        System.out.println("User  именем - " + user1.getName() + " добавлен в базу данных");

        userDao.saveUser(user2.getName(),user2.getLastName(), user2.getAge());
        System.out.println("User  именем - " + user2.getName() + " добавлен в базу данных");

        userDao.saveUser(user3.getName(),user3.getLastName(), user3.getAge());
        System.out.println("User  именем - " + user3.getName() + " добавлен в базу данных");

        for (User tmpUser:
                userDao.getAllUsers()) {
            System.out.println(tmpUser);
        }

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
