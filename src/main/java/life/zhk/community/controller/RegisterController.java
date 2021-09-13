package life.zhk.community.controller;

import life.zhk.community.model.User;
import life.zhk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Map<String,Object> register(User user){

        return userService.register(user);
    }
}
