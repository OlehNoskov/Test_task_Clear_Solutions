package ua.com.clearsolution.service;

import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findByID(Long id);

    DataTableResponse<ENTITY> findByAll(DataTableRequest request);
}