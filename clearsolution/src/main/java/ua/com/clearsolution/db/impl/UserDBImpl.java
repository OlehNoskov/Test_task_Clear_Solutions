package ua.com.clearsolution.db.impl;

import ua.com.clearsolution.db.UserDB;
import ua.com.clearsolution.persistence.entity.User;

import java.util.*;

public class UserDBImpl implements UserDB {
    public static List<User> users = new ArrayList<>();
    private static UserDBImpl instance;
    private static Long idCount = 1L;


    static {
        for (int i = 1; i < 6; i++) {
            User user = new User();
            user.setId(idCount);
            user.setFirstName("Oleg");
            user.setLastName("Noskov");
            user.setBirthday(new Date());
            user.setAddress("Kharkiv");
            user.setEmail("email.com.ua");
            user.setPhone("+38099605647" + i);
            users.add(user);
            idCount++;
        }
    }

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
        entity.setId(idCount);
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setBirthday(entity.getBirthday());
        entity.setAddress(entity.getAddress());
        entity.setEmail(entity.getEmail());
        entity.setPhone(entity.getPhone());
        users.add(entity);
        idCount++;
    }

    @Override
    public void update(User entity) {
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setBirthday(entity.getBirthday());
        entity.setAddress(entity.getAddress());
        entity.setEmail(entity.getEmail());
        entity.setPhone(entity.getPhone());
    }

    @Override
    public void delete(Long id) {
        try {
            users.removeIf(user -> Objects.equals(user.getId(), id));
        } catch (NullPointerException exception) {
            System.out.println("User " + "with " + id + " is not found!");
        }
    }

    @Override
    public User findByID(Long id) {
        return (User) users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().get();
    }

    @Override
    public Collection<User> findAll() {
        return users;
    }
}