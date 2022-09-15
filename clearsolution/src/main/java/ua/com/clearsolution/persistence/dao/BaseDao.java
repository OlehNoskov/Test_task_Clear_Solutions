package ua.com.clearsolution.persistence.dao;

import ua.com.clearsolution.persistence.entity.BaseEntity;

import java.util.Collection;

public interface BaseDao<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

    Collection<ENTITY> findAll();
}