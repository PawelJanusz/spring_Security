package pl.spring.security.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.spring.security.springSecurity.model.LoginUser;

@Controller
public class UserController {

    @RequestMapping("/addUser")
    public String getAddUserView(Model model){
        model.addAttribute("newUser", new LoginUser());
        return "add_login_user";
    }

    @PostMapping("/addLoginUser")
    public String addNewUser(@ModelAttribute LoginUser newUser){
        System.out.println(newUser.getLogin() + " " + newUser.getPassword());
        return "redirect:/index";
    }
}
