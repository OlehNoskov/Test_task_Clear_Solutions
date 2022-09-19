package ua.com.clearsolution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.com.clearsolution.persistence.crud.CrudRepositoryHelper;
import ua.com.clearsolution.persistence.crud.impl.CrudRepositoryHelperImpl;
import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.persistence.repository.UserRepository;
import ua.com.clearsolution.service.UserService;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {
    @Bean
    public CrudRepositoryHelper<User, UserRepository> repositoryHelper() {
        return new CrudRepositoryHelperImpl<>();
    }

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }
}