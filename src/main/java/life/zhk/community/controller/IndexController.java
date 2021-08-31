package life.zhk.community.controller;

import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/index")
    public String hello(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user= userMapper.findByToken(token);
              if(user!=null){
                  request.getSession().setAttribute("user",user);
              }
            break;
            }
        }
        }
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

