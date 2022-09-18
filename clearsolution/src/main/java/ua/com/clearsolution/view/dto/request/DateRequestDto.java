package ua.com.clearsolution.view.dto.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DateRequestDto extends RequestDto {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String dateFrom;
    private String dateTo;

    public Date getParserDateFrom() {
        System.out.println("I am here 1!");
        try {
            return format.parse(getDateFrom());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getParserDateTo() {
        System.out.println("I am here 2!");
        try {
            return format.parse(getDateTo());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}