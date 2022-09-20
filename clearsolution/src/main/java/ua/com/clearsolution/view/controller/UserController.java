package ua.com.clearsolution.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.clearsolution.facade.UserFacade;
import ua.com.clearsolution.view.dto.request.UserRequestDto;
import ua.com.clearsolution.view.dto.response.PageData;
import ua.com.clearsolution.view.dto.response.UserResponseDto;

@Controller
@RequestMapping("/users")
public class UserController extends AbstractController {
    private final UserFacade userFacade;
    private UserRequestDto dateDto = new UserRequestDto();

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("email", "email", "email"),
                new HeaderName("first name", "firstname", "first_name"),
                new HeaderName("last name", "lastname", "first_name"),
                new HeaderName("birth_day", "birth_day", "birth_day"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<UserResponseDto> response = userFacade.findAll(request);
        initDataTable(response, getColumnTitles(), model);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("cardHeader", "All Users");
        return "pages/user/users_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "users");
    }

    @GetMapping("/search/all")
    public String findAllByBirthday(Model model, WebRequest request) {
        PageData<UserResponseDto> response = userFacade.searchAllUsersFromDateToDate(request, dateDto.getParserDateFrom(), dateDto.getParserDateTo());
        initDataTable(response, getColumnTitles(), model);
        model.addAttribute("createUrl", "/users/search/all");
        model.addAttribute("cardHeader", "All search Users");
        return "pages/user/users_all_search_by_birthday";
    }

    @GetMapping("/search")
    public String all(Model model) {
        model.addAttribute("dates", new UserRequestDto());
        return "pages/user/search";
    }

    @PostMapping("/search")
    public String findAllRedirectSearchUsers(@ModelAttribute("dates") UserRequestDto dateRequestDto, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        userFacade.validateDates(dateRequestDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "pages/user/search";
        }
        if (dateRequestDto.isDateFromBeforeDateTo()) {
            dateDto.setDateFrom(dateRequestDto.getDateFrom());
            dateDto.setDateTo(dateRequestDto.getDateTo());
        }
        return "redirect:/users/search/all";
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") UserRequestDto userRequestDto, BindingResult bindingResult) {
        userFacade.validate(userRequestDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "pages/user/user_new";
        }
        userFacade.create(userRequestDto);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        UserResponseDto userResponseDto = userFacade.findById(id);
        model.addAttribute("user", userResponseDto);
        return "pages/user/user_update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") UserRequestDto userRequestDto, BindingResult bindingResult) {
        userFacade.validate(userRequestDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "pages/user/user_update";
        }
        userFacade.update(userRequestDto, id);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        UserResponseDto userResponseDto = userFacade.findById(id);
        model.addAttribute("user", userResponseDto);
        return "pages/user/user_details";
    }
}