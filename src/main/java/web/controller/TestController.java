package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String allUsers(Model model)  {
        List<User> userList = userService.listUsers();
        model.addAttribute("userList", userList);
        return "tableUsers";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }


    // Получаем значения из формы, там создаем объект и добавляем в бд
    @RequestMapping("/addInDB")
    public String addInDB(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {

        return "redirect:/";
    }
}
