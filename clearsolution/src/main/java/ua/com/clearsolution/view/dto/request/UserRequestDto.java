package ua.com.clearsolution.view.dto.request;

import lombok.Data;

@Data
public class UserRequestDto extends RequestDto {
    private String email;
    private String firstname;
    private String lastname;
    private String birthday;
    private String city;
    private String phone;
}