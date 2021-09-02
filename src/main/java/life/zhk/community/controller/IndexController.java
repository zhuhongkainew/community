package life.zhk.community.controller;

import life.zhk.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/index")
    public String hello() {

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

    @GetMapping("/gopublish")
    public  String goPublish(){
        return "publish";
    }
    @GetMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){

        request.getSession().invalidate();
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

     return "redirect:/";
    }
}

