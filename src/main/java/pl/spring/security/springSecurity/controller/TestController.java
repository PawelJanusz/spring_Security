package pl.spring.security.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/status")
    public String getStatusInfo(){
        return "Status: the best";
    }

}
