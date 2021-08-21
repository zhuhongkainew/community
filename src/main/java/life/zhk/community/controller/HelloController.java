package life.zhk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name") String name, @RequestParam(name = "aihao") String aihao, Model model) {
        System.out.println("进来");
        model.addAttribute("name", name);
        model.addAttribute("aihao", aihao);
        return "hello";
    }
}

