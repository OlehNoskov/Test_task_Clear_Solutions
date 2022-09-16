package ua.com.clearsolution.persistence.crud.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ua.com.clearsolution.persistence.crud.CrudRepositoryHelper;
import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.BaseEntity;
import ua.com.clearsolution.persistence.repository.AbstractRepository;

import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl <
        E extends BaseEntity,
        R extends AbstractRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    @Override
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(R repository, DataTableRequest dataTableRequest) {
        int page = dataTableRequest.getPage() - 1;
        int size = dataTableRequest.getSize();
        String sortParam = dataTableRequest.getSort();
        String orderParam = dataTableRequest.getOrder();

        Sort sort = orderParam.equals("desc")
                ? Sort.by(sortParam).descending()
                : Sort.by(sortParam).ascending();

        if (MapUtils.isNotEmpty(dataTableRequest.getRequestParamMap())) {
            System.out.println("dataTableRequest = " + dataTableRequest.getRequestParamMap());
        }

        PageRequest request = PageRequest.of(page, size, sort);

        Page<E> pageEntity = repository.findAll(request);

        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setSort(sortParam);
        dataTableResponse.setOrder(orderParam);
        dataTableResponse.setPageSize(size);
        dataTableResponse.setCurrentPage(page);
        dataTableResponse.setItemsSize(pageEntity.getTotalElements());
        dataTableResponse.setSize(pageEntity.getTotalPages());
        dataTableResponse.setItems(pageEntity.getContent());

        return dataTableResponse;
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found");
        }
    }
}