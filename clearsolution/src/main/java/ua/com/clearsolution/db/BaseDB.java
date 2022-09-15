package ua.com.clearsolution.db;

import ua.com.clearsolution.persistence.entity.BaseEntity;

import java.io.IOException;
import java.util.Collection;

public interface BaseDB<ENTITY extends BaseEntity> {
    void create(ENTITY entity) throws IOException;

    void update(ENTITY entity);

    void delete(Long id);

    ENTITY findByID(Long id);

    Collection<ENTITY> findAll();
}