package life.zhk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class questionController {
@GetMapping("/question/{id}")
    public String questById(@PathVariable(name="id") Integer id){

        return "publish";
    }
}
