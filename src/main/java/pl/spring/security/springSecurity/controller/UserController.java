package pl.spring.security.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.spring.security.springSecurity.model.LoginUser;
import pl.spring.security.springSecurity.service.UserLoginService;

@Controller
public class UserController {

    private final UserLoginService userLoginService;

    @Autowired
    public UserController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @RequestMapping("/addUser")
    public String getAddUserView(Model model){
        model.addAttribute("newUser", new LoginUser());
        return "add_login_user";
    }

    @PostMapping("/addLoginUser")
    public String addNewUser(@ModelAttribute LoginUser newUser){
        System.out.println(newUser.getLogin() + " " + newUser.getPassword());
        userLoginService.addLoginUser(newUser);
        return "redirect:/index";
    }
}
