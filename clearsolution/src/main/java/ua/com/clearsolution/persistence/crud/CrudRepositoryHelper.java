package ua.com.clearsolution.persistence.crud;

import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;
import ua.com.clearsolution.persistence.repository.AbstractRepository;

import java.util.Optional;

public interface CrudRepositoryHelper <E extends BaseEntity, R extends AbstractRepository<E>>{
    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    DataTableResponse<E> findAll(R repository, DataTableRequest dataTableRequest);
}