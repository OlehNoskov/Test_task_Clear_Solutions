package ua.com.clearsolution.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    private final UserFacade studentFacade;

    public UserController(UserFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("email", "email", "email"),
                new HeaderName("first name", "firstname", "first_name"),
                new HeaderName("last name", "lastname", "first_name"),
//                new HeaderName("age", "age", "age"),
                new HeaderName("city", "city", "city"),
                new HeaderName("phone", "phone", "phone"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<UserResponseDto> response = studentFacade.findAll(request);
        initDataTable(response, getColumnTitles(), model);
        model.addAttribute("createUrl", "/users/all");
        model.addAttribute("cardHeader", "All Users");
        return "pages/user/users_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "users");
    }

    @GetMapping("/new")
    public String redirectToNewStudentPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/new")
    public String createNewStudent(@ModelAttribute("user") UserRequestDto userRequestDto) {
        studentFacade.create(userRequestDto);
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("user") UserRequestDto userRequestDto) {
        studentFacade.update(userRequestDto, id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        UserResponseDto userResponseDto = studentFacade.findById(id);
        model.addAttribute("user", userResponseDto);
        return "pages/user/user_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        UserResponseDto userResponseDto = studentFacade.findById(id);
        model.addAttribute("user", userResponseDto);
        return "pages/user/user_details";
    }
}