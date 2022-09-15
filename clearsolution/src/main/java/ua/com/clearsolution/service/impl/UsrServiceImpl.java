package ua.com.clearsolution.service.impl;

import ua.com.clearsolution.persistence.dao.UserDao;
import ua.com.clearsolution.persistence.dao.impl.UserDaoImpl;
import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.service.UserService;

import java.util.Collection;

public class UsrServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(String id) {
        userDao.delete(Long.valueOf(id));
    }

    @Override
    public User findByID(String id) {
        return userDao.findById(Long.valueOf(id));
    }

    @Override
    public Collection<User> findByAll() {
        return userDao.findAll();
    }
}