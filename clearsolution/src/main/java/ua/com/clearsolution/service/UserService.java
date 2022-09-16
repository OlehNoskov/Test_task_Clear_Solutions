package ua.com.clearsolution.service;

import ua.com.clearsolution.persistence.entity.User;

import java.util.List;

public interface UserService extends BaseService<User>{
    List<User> findAll();
}