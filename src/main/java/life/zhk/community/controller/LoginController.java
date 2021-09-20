package life.zhk.community.controller;

import cn.hutool.crypto.SecureUtil;
import life.zhk.community.model.User;
import life.zhk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Map<String,Object> sign(User user,
                    HttpServletResponse response

    ) {
        Map<String,Object> objectMap =userService.loginUser(user,response);
        //User user = userMapper.findById(Integer.parseInt(name));
        //User user1 = userMapper.selectByPrimaryKey(Integer.parseInt(user.getName()));

//        if (user != null) {
//            response.addCookie(new Cookie("token", user1.getToken()));
//
//        } else {
//
//        }

    return objectMap;
    }

    public static void main(String[] args) {
        String a =SecureUtil.md5("12345678" + "bkia8q");
        System.out.println(a);
    }
}
