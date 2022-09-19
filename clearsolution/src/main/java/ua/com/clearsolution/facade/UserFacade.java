package ua.com.clearsolution.facade;

import org.springframework.validation.Errors;
import org.springframework.web.context.request.WebRequest;

import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.view.dto.request.UserRequestDto;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.UserResponseDto;

import java.util.Date;
import java.util.List;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {
    PageData<UserResponseDto> searchAllUsersFromDateToDate(WebRequest request, Date from, Date to);
    List<User> findAll();
    void validateDates(Object target, Errors errors);
}