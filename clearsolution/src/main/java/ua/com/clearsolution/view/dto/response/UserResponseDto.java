package ua.com.clearsolution.view.dto.response;


import lombok.Getter;
import ua.com.clearsolution.persistence.entity.User;

import java.util.Date;

public class UserResponseDto extends ResponseDto {
    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private Date birthday;
    @Getter
    private String address;
    @Getter
    private String phone;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthday = user.getBirthday();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }
}