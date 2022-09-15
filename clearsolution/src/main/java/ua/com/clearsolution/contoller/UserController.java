package ua.com.clearsolution.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String findAll() {
        return "pages/student/student_all";
    }

//    @PostMapping("/all")
//    public ModelAndView findAllRedirect() {
//        return findAllRedirect(request, model, "students");
//    }

//    @GetMapping("/new")
//    public String redirectToNewStudentPage(Model model) {
//        return "pages/student/student_new";
//    }

//    @PostMapping("/new")
//    public String createNewStudent(@ModelAttribute("student") StudentRequestDto studentRequestDto) {
//        studentFacade.create(studentRequestDto);
//        return "redirect:/students";
//    }

//    @PostMapping("/update/{id}")
//    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentRequestDto studentRequestDto) {
//        studentFacade.update(studentRequestDto, id);
//        return "redirect:/students";
//    }

//    @GetMapping("/update/{id}")
//    public String update(@PathVariable Long id, Model model) {
//        return "pages/student/student_update";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        return "redirect:/students";
//    }
//
//    @GetMapping("/details/{id}")
//    public String details(@PathVariable Long id, Model model) {
//        return "pages/student/student_details";
//    }
}