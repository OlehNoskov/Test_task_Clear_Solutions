package ua.com.clearsolution.service;

import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;

import java.util.Optional;

public interface BaseService <ENTITY extends BaseEntity>{
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    Optional<ENTITY> findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request);
}