package life.zhk.community.controller;

import life.zhk.community.dto.CommentDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentDto commentDto,
                                       HttpServletRequest request) {

        return null;
    }
}
