package ua.com.clearsolution.persistence.entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String address;
    private String phone;
}