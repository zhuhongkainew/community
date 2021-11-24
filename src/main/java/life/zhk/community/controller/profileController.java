package life.zhk.community.controller;

import life.zhk.community.dto.PaginationDto;
import life.zhk.community.model.User;
import life.zhk.community.service.NotificationService;
import life.zhk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller

public class profileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if("question".equals(action)){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的提问");
            PaginationDto paginationDto =questionService.getPaginationListByUserId(user.getId(),page,size);
            model.addAttribute("pagination", paginationDto);
        }

        if("replies".equals(action)){
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "我的回复");
            PaginationDto paginationDto =notificationService.getPaginationListByUserId(user.getId(),page,size);
            model.addAttribute("pagination", paginationDto);

        }


        return "profile";
    }
}
