package ua.com.clearsolution.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.clearsolution.view.dto.request.UserRequestDto;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.UserResponseDto;

import java.util.Date;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto> {
    PageData<UserResponseDto> searchAllUsersFromDateToDate(WebRequest request, Date from, Date to);
}