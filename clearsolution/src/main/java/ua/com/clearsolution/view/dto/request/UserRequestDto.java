package ua.com.clearsolution.view.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserRequestDto extends RequestDto {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String dateFrom;
    private String dateTo;
    private String email;
    private String firstname;
    private String lastname;
    private String birthday;
    private String city;
    private String phone;

    public Date getParserDateFrom() {
        try {
            return format.parse(getDateFrom());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getParserDateTo() {
        try {
            return format.parse(getDateTo());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDateFromBeforeDateTo() {
        return getParserDateFrom().before(getParserDateTo());
    }

    public Date getParserDateBirthday() {
        try {
            return format.parse(getBirthday());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}