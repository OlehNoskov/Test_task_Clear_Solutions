package ua.com.clearsolution.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService studentService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    public UserFacadeImpl(UserService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        setAllFieldsUser(userRequestDto, user, userRequestDto.getPhone());
        studentService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto, long id) {
        User user = studentService.findById(id).get();
        setAllFieldsUser(userRequestDto, user, user.getPhone());
        studentService.update(user);
    }

    private void setAllFieldsUser(UserRequestDto userRequestDto, User user, String phone) {
        user.setEmail(userRequestDto.getEmail());
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        try {
            if (isInputDateValid(format.parse(userRequestDto.getBirthday())) && isUserAgeValid(format.parse(userRequestDto.getBirthday()))) {
                user.setBirthDay(format.parse(userRequestDto.getBirthday()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        user.setCity(userRequestDto.getCity());
        user.setPhone(phone);
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
        pageData.initPaginationState();
        return pageData;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> allUsers = studentService.findAll();
        List<UserResponseDto> list = allUsers.stream().map(UserResponseDto::new).collect(Collectors.toList());
        return list;
    }

    private boolean isInputDateValid(Date userBirthday) {
        return userBirthday.before(new Date(System.currentTimeMillis()));
    }

    private boolean isUserAgeValid(Date userBirthday) {
        int minAge = 0;
        try (InputStream input = new FileInputStream("validation.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            minAge = Integer.parseInt((String) properties.get("age"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (Period.between(convertToLocalDateViaInstant(userBirthday), convertToLocalDateViaInstant(new Date(System.currentTimeMillis()))).getYears() >= minAge) {
            return true;
        } else {
            return false;
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "date.input.error");
        try {
            if (!isInputDateValid(format.parse(userRequestDto.getBirthday())) || !isUserAgeValid(format.parse(userRequestDto.getBirthday()))) {
                errors.rejectValue("birthday", "date.input.error");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}