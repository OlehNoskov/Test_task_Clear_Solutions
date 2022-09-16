package ua.com.clearsolution.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.clearsolution.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository <E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}