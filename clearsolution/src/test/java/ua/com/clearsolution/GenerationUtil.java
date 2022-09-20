package ua.com.clearsolution;

import ua.com.clearsolution.persistence.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class GenerationUtil {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static User generate(String firstName, String lastName, String email) {
        User user = new User();
        try {
            user.setBirthDay(format.parse("1990-10-10"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        user.setCity("Kharkiv");
        user.setPhone("+380501567425");
        return user;
    }
}