package ua.com.clearsolution.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.clearsolution.persistence.entity.User;

@Repository
public interface UserRepository extends AbstractRepository<User>{
}