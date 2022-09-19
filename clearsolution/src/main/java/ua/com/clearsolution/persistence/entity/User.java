package ua.com.clearsolution.persistence.entity;

import groovy.transform.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    public User() {
        super();
    }

    @Column(nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "address")
    private String city;

    @Column(name = "phone")
    private String phone;
}