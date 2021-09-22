package life.zhk.community.controller;

import life.zhk.community.dto.CommentDto;
import life.zhk.community.dto.ResultDto;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.mapper.CommentMapper;
import life.zhk.community.model.Comment;
import life.zhk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/comment")
    public Object comment(CommentDto commentDto,
                          HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setParentId(commentDto.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());

        comment.setType(commentDto.getType());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDto.errorOf(ExceptionEnum.USER_NO_LOGIN);
        }
        comment.setCommentatorId(user.getId());
        commentMapper.insert(comment);
        return ResultDto.errorOf(200,"回复成功");
    }
}