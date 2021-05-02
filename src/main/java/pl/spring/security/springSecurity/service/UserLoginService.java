package pl.spring.security.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.spring.security.springSecurity.model.LoginUser;
import pl.spring.security.springSecurity.repository.UserLoginRepo;

@Service
public class UserLoginService {

    private final UserLoginRepo userLoginRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoginService(UserLoginRepo userLoginRepo, PasswordEncoder passwordEncoder) {
        this.userLoginRepo = userLoginRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addLoginUser(LoginUser loginUser){
        loginUser.setRole("ROLE_USER");
        loginUser.setPassword(passwordEncoder.encode(loginUser.getPassword()));
        userLoginRepo.save(loginUser);
    }
}
