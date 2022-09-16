package ua.com.clearsolution.view.dto.request;

import lombok.Data;
import java.util.Date;

@Data
public class UserRequestDto extends RequestDto{
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String address;
    private String phone;
}