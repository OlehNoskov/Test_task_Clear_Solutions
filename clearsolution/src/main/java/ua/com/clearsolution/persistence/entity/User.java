package ua.com.clearsolution.persistence.entity;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    public User() {
        super();
    }
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(name = "address")
    private String city;

    @Column(name = "phone")
    private String phone;
}