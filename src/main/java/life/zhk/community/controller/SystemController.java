package life.zhk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {
    @GetMapping("/goRegister")
    public  String register(){
        return "register";
    }
}
