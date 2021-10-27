package life.zhk.community.controller;

import life.zhk.community.cache.TagCache;
import life.zhk.community.dto.PaginationDto;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String hello(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        Model model) {
        Integer size =5;
        PaginationDto pagination = questionService.getPaginationList(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }

    @GetMapping("/radio")
    public String radio() {
        return "radio";
    }

    @GetMapping("/goLogin")
    public String login() {
        return "login";
    }

    @GetMapping("/gopublish")
    public String goPublish(Model model) {
        model.addAttribute("tags", TagCache.getTag());
        return "publish";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}

