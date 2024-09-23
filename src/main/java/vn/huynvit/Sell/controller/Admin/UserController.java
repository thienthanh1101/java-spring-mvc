package vn.huynvit.sell.controller.Admin;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import vn.huynvit.sell.domain.User;
import vn.huynvit.sell.service.UploadService;
import vn.huynvit.sell.service.UserService;

import org.springframework.ui.Model;

@Controller
public class UserController {
    // DI dependency injection
    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UploadService uploadService, UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping("/")
    public String getHomepage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("huynv75@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("jonh", "test");
        model.addAttribute("jonh2", "form controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        // System.out.println("Check Path:" + id);
        System.out.println("Check user detail:" + user);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User huynv,
            @RequestParam("huynvitFile") MultipartFile file) {

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        // System.out.println("test " + avatar);
        // this.userService.handleSaveUser(huynv);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User huynv) {
        User currentUser = this.userService.getUserById(huynv.getId());
        if (currentUser != null) {
            currentUser.setAddress(huynv.getAddress());
            currentUser.setFullName(huynv.getFullName());
            currentUser.setPhone(huynv.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User huynv) {
        this.userService.DeleteUser(huynv.getId());
        return "redirect:/admin/user";
    }
}