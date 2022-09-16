package ua.com.clearsolution.service.impl;

import org.springframework.stereotype.Service;
import ua.com.clearsolution.persistence.dao.UserDao;
import ua.com.clearsolution.persistence.dao.impl.UserDaoImpl;
import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.service.UserService;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
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
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findByID(Long id) {
        return userDao.findById(id);
    }

    @Override
    public DataTableResponse<User> findByAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Collection<User> findByAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}