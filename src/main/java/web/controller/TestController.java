package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String allUsers(Model model)  {
        List<User> userList = userService.listUsers();
        model.addAttribute("userList", userList);
        return "tableUsers";
    }

    @RequestMapping("/admin/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    // Получаем значения из формы, там создаем объект и добавляем в бд
    @RequestMapping(value = "/admin/addInDB")
    public String addInDB(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{username}/edit")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.getUser(username));
        return "update";
    }

    @PatchMapping("/admin/{username}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("username") String username) {
        userService.edit(username, user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{username}/delete")
    public String delete(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect:/admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userInfo(Principal principal, Model model) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "userInfo";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}
