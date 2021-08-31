package life.zhk.community.controller;

import life.zhk.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/index")
    public String hello(HttpServletRequest request) {
        return "index";
    }
    @GetMapping("/radio")
    public  String radio(){
       return "radio";
    }
    @GetMapping("/")
    public  String login(){
        return "login";
    }
}

