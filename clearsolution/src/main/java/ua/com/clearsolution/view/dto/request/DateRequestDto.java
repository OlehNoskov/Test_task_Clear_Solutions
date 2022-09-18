package ua.com.clearsolution.view.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DateRequestDto {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String dateFrom;
    private String dateTo;

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


    public static Date getParserDate(String date) throws ParseException {
        return format.parse(date);
    }
}