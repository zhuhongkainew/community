package life.zhk.community.controller;

import life.zhk.community.dto.QuestionDto;
import life.zhk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class questionController {
    @Autowired
    private QuestionService questionService;
@GetMapping("/question/{id}")
    public String questById(@PathVariable(name="id") Integer id, Model model){
    QuestionDto questionDto =questionService.getQuestionById(id);
    model.addAttribute("question",questionDto);
        return "question";
    }
}
