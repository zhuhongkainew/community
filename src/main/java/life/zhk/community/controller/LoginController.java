package life.zhk.community.controller;

import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign")
    public String sign(@RequestParam(name = "email") String id,
                       @RequestParam(name = "password") String name,
                       HttpServletRequest request,
                       HttpServletResponse response

    ) {
       User user= userMapper.findById(name);
       if(user !=null){
           response.addCookie(new Cookie("token",user.getToken()));
           return "redirect:/index";
       }else {
           return "redirect:/";
       }

    }
}
