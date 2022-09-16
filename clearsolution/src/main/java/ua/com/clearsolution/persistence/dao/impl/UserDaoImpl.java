package ua.com.clearsolution.persistence.dao.impl;

import ua.com.clearsolution.db.UserDB;
import ua.com.clearsolution.db.impl.UserDBImpl;
import ua.com.clearsolution.persistence.dao.UserDao;
import ua.com.clearsolution.persistence.entity.User;

import java.io.IOException;
import java.util.Collection;

public class UserDaoImpl implements UserDao {
    private final UserDB userDB = UserDBImpl.getInstance();

    @Override
    public void create(User entity) {
        try {
            userDB.create(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        userDB.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDB.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDB.findByID(id);
    }

    @Override
    public Collection<User> findAll() {
        return userDB.findAll();
    }
}