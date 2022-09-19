package ua.com.clearsolution.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
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

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    public UserFacadeImpl(UserService studentService) {
        this.userService = studentService;
    }

    @Override
    public User createAndReturn(UserRequestDto userRequestDto) {
        User user = new User();
        setAllFieldsUser(userRequestDto, user);
        userService.create(user);
        return user;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        setAllFieldsUser(userRequestDto, user);
        userService.create(user);
    }

    @Override
    public User updateAndReturn(UserRequestDto userRequestDto, long id) {
        User user = userService.findById(id).get();
        setAllFieldsUser(userRequestDto, user);
        userService.update(user);
        return user;
    }

    @Override
    public void update(UserRequestDto userRequestDto, long id) {
        User user = userService.findById(id).get();
        setAllFieldsUser(userRequestDto, user);
        userService.update(user);
    }

    private void setAllFieldsUser(UserRequestDto userRequestDto, User user) {
        if (isEmailValid(userRequestDto.getEmail())) {
            user.setEmail(userRequestDto.getEmail());
        }
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());

        if (isInputDateValid(userRequestDto.getParserDateBirthday()) && isUserAgeValid(userRequestDto.getParserDateBirthday())) {
            user.setBirthDay(userRequestDto.getParserDateBirthday());
        }
        user.setCity(userRequestDto.getCity());
        user.setPhone(userRequestDto.getPhone());
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(long id) {
        return new UserResponseDto(userService.findById(id).get());
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

        DataTableResponse<User> all = userService.findAll(dataTableRequest);

        return getUserResponseDtoPageData(pageAndSizeData, sortData, all);
    }

    @Override
    public PageData<UserResponseDto> searchAllUsersFromDateToDate(WebRequest request, Date from, Date to) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        PageData<UserResponseDto> all = findAll(request);
        all.setItems(all.getItems().stream()
                .filter(user -> user.getBirthday().after(from) && user.getBirthday().before(to))
                .collect(Collectors.toList()));
        return all;
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    private PageData<UserResponseDto> getUserResponseDtoPageData(PageAndSizeData pageAndSizeData, SortData sortData, DataTableResponse<User> all) {
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

    private boolean isEmailValid(String email) {
        String regexEmail = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isInputDateValid(Date userBirthday) {
        return userBirthday.before(new Date(System.currentTimeMillis()));
    }

    private boolean isUserAgeValid(Date userBirthday) {
        int minAge = 0;
        try (InputStream input = new FileInputStream("validation.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            minAge = Integer.parseInt((String) properties.get("min.age"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Period.between(convertToLocalDateViaInstant(userBirthday), convertToLocalDateViaInstant(new Date(System.currentTimeMillis()))).getYears() >= minAge;
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
        if (!isInputDateValid(userRequestDto.getParserDateBirthday()) || !isUserAgeValid(userRequestDto.getParserDateBirthday())) {
            errors.rejectValue("birthday", "date.input.error");
        }
    }

    @Override
    public void validateDates(Object target, Errors errors) {
        UserRequestDto userRequestDto = (UserRequestDto) target;
        if (!userRequestDto.isDateFromBeforeDateTo()) {
            errors.rejectValue("dates", "date.input.error");
        }
    }
}