package life.zhk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    @GetMapping("/goSign")
    public String goSign() {
        return "sign";
    }
}
