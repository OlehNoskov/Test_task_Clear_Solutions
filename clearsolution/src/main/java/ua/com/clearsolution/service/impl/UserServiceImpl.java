package ua.com.clearsolution.service.impl;

import org.springframework.stereotype.Service;

import ua.com.clearsolution.persistence.crud.CrudRepositoryHelper;
import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.persistence.repository.UserRepository;
import ua.com.clearsolution.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final CrudRepositoryHelper<User, UserRepository> repositoryHelper;
    private final UserRepository userRepository;

    public UserServiceImpl(CrudRepositoryHelper<User, UserRepository> repositoryHelper,
                           UserRepository userRepository) {
        this.repositoryHelper = repositoryHelper;
        this.userRepository = userRepository;
    }

    @Override
    public void create(User entity) {
        repositoryHelper.create(userRepository, entity);
    }

    @Override
    public User createAndReturn(User entity) {
        repositoryHelper.create(userRepository, entity);
        return entity;
    }

    @Override
    public User updateAndReturn(User entity) {
        repositoryHelper.update(userRepository, entity);
        return entity;
    }

    @Override
    public void update(User entity) {
        repositoryHelper.update(userRepository, entity);
    }

    @Override
    public void delete(Long id) {
        repositoryHelper.delete(userRepository, id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repositoryHelper.findById(userRepository, id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(userRepository, dataTableRequest);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}