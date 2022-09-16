package ua.com.clearsolution.facade;

import ua.com.clearsolution.view.dto.request.UserRequestDto;
import ua.com.clearsolution.view.dto.response.UserResponseDto;

import java.util.List;

public interface UserFacade extends BaseFacade<UserRequestDto, UserResponseDto>{
    List<UserResponseDto> findAll();
}