package life.zhk.community.controller;

import life.zhk.community.dto.QuestionDto;
import life.zhk.community.model.Question;
import life.zhk.community.model.User;
import life.zhk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish(@RequestParam(name = "title") String title,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "tag") String tag,
                          @RequestParam(name = "id") Integer id,
                          HttpServletRequest request,
                          Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if ("".equals(title) || title == null) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if ("".equals(description) || description == null) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if ("".equals(tag) || tag == null) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        User user = (User) request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        //questionMapper.createPublish(question);
        questionService.createdOrUpdateQuestion(question);
        return "redirect:/index";
    }

    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable(name = "id") Integer id,
                               Model model) {
        QuestionDto questionDto = questionService.getQuestionById(id);
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("tag", questionDto.getTag());
        model.addAttribute("description", questionDto.getDescription());
        model.addAttribute("id", questionDto.getId());
        return "publish";
    }
}
