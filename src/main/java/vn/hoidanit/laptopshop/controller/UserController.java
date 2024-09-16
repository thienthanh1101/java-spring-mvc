package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    // DI dependency injection
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("jonh", "test");
        model.addAttribute("jonh2", "form controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserpage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUserpage(Model model, @ModelAttribute("newUser") User huynv) {
        System.out.println("Run here" + huynv);
        this.userService.hanleSaveUser(huynv);
        return "hello";
    }
}