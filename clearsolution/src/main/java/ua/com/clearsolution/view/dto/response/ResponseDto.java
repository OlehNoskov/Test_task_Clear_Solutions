package ua.com.clearsolution.view.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public abstract class ResponseDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private Date birthday;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String phone;
}