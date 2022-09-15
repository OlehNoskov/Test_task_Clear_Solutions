package ua.com.clearsolution.service;

import ua.com.clearsolution.persistence.entity.BaseEntity;

import java.util.Collection;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(String id);

    ENTITY findByID(String id);

    Collection<ENTITY> findByAll();
}