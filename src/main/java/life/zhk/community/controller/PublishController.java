package life.zhk.community.controller;

import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.model.Question;
import life.zhk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish(@RequestParam(name ="title") String title,
                          @RequestParam(name="description") String description,
                          @RequestParam(name="tag") String tag,
                          HttpServletRequest request,
                          Model model
                          ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if("".equals(title)||title==null){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if("".equals(description)||description==null){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if("".equals(tag)||tag==null){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        User user= (User) request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        questionMapper.createPublish(question);
        return "publish";
    }
}
