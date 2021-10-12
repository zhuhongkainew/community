package life.zhk.community.controller;

import life.zhk.community.dto.CommentDto;
import life.zhk.community.dto.QuestionDto;
import life.zhk.community.enums.CommentTypeEnum;
import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.service.CommentService;
import life.zhk.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class questionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String questById(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDto questionDto = questionService.getQuestionById(id);
        if (questionDto == null) {
            throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
        }
        List<CommentDto> commentDto =commentService.getCommentByParentId(id, CommentTypeEnum.QUESTION);

        questionService.incView(id);
        model.addAttribute("question", questionDto);
        model.addAttribute("CommentList", commentDto);
        return "question";
    }
}
