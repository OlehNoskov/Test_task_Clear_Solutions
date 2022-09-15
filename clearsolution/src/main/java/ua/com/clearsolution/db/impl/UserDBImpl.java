package ua.com.clearsolution.db.impl;

import ua.com.clearsolution.db.UserDB;
import ua.com.clearsolution.persistence.entity.User;

import java.util.Collection;

public class UserDBImpl implements UserDB {
    private static UserDBImpl instance;

    private UserDBImpl() {

    }

    public static UserDBImpl getInstance() {
        if (instance == null) {
            instance = new UserDBImpl();
        }
        return instance;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public User findByID(Long id) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}