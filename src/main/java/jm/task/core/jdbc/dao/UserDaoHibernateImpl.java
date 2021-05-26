package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Query query = session .createSQLQuery("CREATE TABLE IF NOT EXISTS Users(userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                " userName varchar(45) not null ," +
                " userLastName varchar(45) not null ," +
                " userAge TINYINT(8) not null )  ");
        query.executeUpdate();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> result = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        transaction.commit();
        session.close();

    }
}
