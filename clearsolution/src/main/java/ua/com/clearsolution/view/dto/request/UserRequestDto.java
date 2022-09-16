package ua.com.clearsolution.view.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserRequestDto extends RequestDto {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private Date birthday;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String phone;
}