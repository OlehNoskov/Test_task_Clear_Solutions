package ua.com.clearsolution.service;

import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;
import ua.com.clearsolution.persistence.entity.User;

import java.util.Optional;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    User createAndReturn(User entity);

    void update(ENTITY entity);

    User updateAndReturn(User entity);

    void delete(Long id);

    Optional<ENTITY> findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);
}