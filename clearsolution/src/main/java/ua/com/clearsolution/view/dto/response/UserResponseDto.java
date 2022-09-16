package ua.com.clearsolution.view.dto.response;

import lombok.Getter;
import ua.com.clearsolution.persistence.entity.User;

import java.util.Date;

public class UserResponseDto extends ResponseDto {
    @Getter
    private String email;
    @Getter
    private String firstname;
    @Getter
    private String lastname;
    @Getter
    private Date birthday;
    @Getter
    private String city;
    @Getter
    private String phone;
//      @Getter
//    private Integer age;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        setId(user.getId());
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthday = user.getBirthday();
        this.city = user.getCity();
        this.phone = user.getPhone();
//        this.age = student.getAge();
    }
}