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

    private String day;
    private String months;
    private String year;

    public UserResponseDto(User user) {
        setId(user.getId());
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthday = user.getBirthDay();
        this.city = user.getCity();
        this.phone = user.getPhone();
    }

    public String getDay() {
        return birthday.getDay() + "";
    }

    public String getMonths() {
        return birthday.getMonth() + "";
    }

    public String getYear() {
        return birthday.getYear() + "";
    }
}