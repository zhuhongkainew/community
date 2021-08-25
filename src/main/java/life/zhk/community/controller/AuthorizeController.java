package life.zhk.community.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AuthorizeController {
    @Value("${github.community.clientid}")
    private String clientid;
    @Value("${github.community.secretid}")
    private String secretid;
    @Value("${github.community.redirect}")
    private String redirect;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){

        return "redirect:/";
    }
}
