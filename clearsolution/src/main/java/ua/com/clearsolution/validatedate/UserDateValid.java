package ua.com.clearsolution.validatedate;

import ua.com.clearsolution.view.dto.request.UserRequestDto;

import java.util.Calendar;
import java.util.Date;

public class UserDateValid {
    public static Date userValidDate(UserRequestDto userRequestDto) {

        String day = userRequestDto.getDay();
        String months = userRequestDto.getMonths();
        String year = userRequestDto.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(months) - 1, Integer.parseInt(day));
        Date date = calendar.getTime();

        if (ExaminationValidInputDataCalendar.calendarIsValid(Integer.parseInt(year),
                Integer.parseInt(months),
                Integer.parseInt(day))) {
            return date;
        }
        return null;
    }
}