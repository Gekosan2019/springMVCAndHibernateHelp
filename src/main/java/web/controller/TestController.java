package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
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

    @GetMapping("/{username}/edit")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.getUser(username));
        return "update";
    }

    @PatchMapping("/{username}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("username") String username) {
        userService.edit(username, user);
        return "redirect:/";
    }

    @GetMapping("/{username}/delete")
    public String delete(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect:/";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}
