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
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setBirthday(userRequestDto.getBirthday());
        user.setAddress(userRequestDto.getAddress());
        user.setPhone(userRequestDto.getPhone());

        userService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto, long id) {
        User user = userService.findByID(id);
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setBirthday(userRequestDto.getBirthday());
        user.setAddress(userRequestDto.getAddress());
        user.setPhone(userRequestDto.getPhone());

        userService.update(user);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(long id) {
        return new UserResponseDto(userService.findByID(id));
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

        Map<String, String[]> parameterMap = request.getParameterMap();

        DataTableResponse<User> all = userService.findByAll(dataTableRequest);

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
        List<User> allStudents = userService.findAll();
        List<UserResponseDto> list = allStudents.stream().map(UserResponseDto::new).collect(Collectors.toList());
        return list;
    }
}