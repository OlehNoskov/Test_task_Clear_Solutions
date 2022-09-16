package ua.com.clearsolution.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.clearsolution.facade.UserFacade;
import ua.com.clearsolution.persistence.datatable.DataTableRequest;
import ua.com.clearsolution.persistence.datatable.DataTableResponse;
import ua.com.clearsolution.persistence.entity.User;
import ua.com.clearsolution.service.UserService;
import ua.com.clearsolution.util.WebRequestUtil;
import ua.com.clearsolution.view.dto.request.PageAndSizeData;
import ua.com.clearsolution.view.dto.request.SortData;
import ua.com.clearsolution.view.dto.request.UserRequestDto;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService studentService;

    public UserFacadeImpl(UserService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        user.setBirthday(userRequestDto.getBirthday());
        user.setCity(userRequestDto.getCity());
        user.setPhone(user.getPhone());
//        student.setAge(studentRequestDto.getAge());
        studentService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto, long id) {
        User user = studentService.findById(id).get();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        user.setBirthday(userRequestDto.getBirthday());
        user.setCity(userRequestDto.getCity());
        user.setPhone(user.getPhone());
//        student.setAge(studentRequestDto.getAge());
        studentService.update(user);
    }

    @Override
    public void delete(long id) {
        studentService.delete(id);
    }

    @Override
    public UserResponseDto findById(long id) {
        return new UserResponseDto(studentService.findById(id).get());
    }

    @Override
    public PageData<UserResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<User> all = studentService.findAll(dataTableRequest);

        List<UserResponseDto> list = all.getItems().
                stream().
                map(UserResponseDto::new).
                collect(Collectors.toList());

        PageData<UserResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> allUsers = studentService.findAll();
        List<UserResponseDto> list = allUsers.stream().map(UserResponseDto::new).collect(Collectors.toList());
        return list;
    }
}