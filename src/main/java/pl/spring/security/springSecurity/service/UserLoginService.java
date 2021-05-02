package pl.spring.security.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.spring.security.springSecurity.model.LoginUser;
import pl.spring.security.springSecurity.repository.UserLoginRepo;

@Service
public class UserLoginService {

    private final UserLoginRepo userLoginRepo;

    @Autowired
    public UserLoginService(UserLoginRepo userLoginRepo) {
        this.userLoginRepo = userLoginRepo;
    }

    public void addLoginUser(LoginUser loginUser){
        userLoginRepo.save(loginUser);
    }
}
