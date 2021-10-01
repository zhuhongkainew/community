package life.zhk.community.controller;

import life.zhk.community.dto.CommentCreateDto;
import life.zhk.community.dto.ResultDto;
import life.zhk.community.enums.CommentTypeEnum;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.model.Comment;
import life.zhk.community.model.User;
import life.zhk.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentController {

@Autowired
private CommentService commentService;
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentCreateDto commentCreateDto,
                          HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setContent(commentCreateDto.getContent());
        comment.setParentId(commentCreateDto.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);

        comment.setType(commentCreateDto.getType());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDto.errorOf(ExceptionEnum.USER_NO_LOGIN);
        }
        if(commentCreateDto.getContent()==null || StringUtils.isBlank(commentCreateDto.getContent())){
            return ResultDto.errorOf(ExceptionEnum.CONTENT_EMPTY);
        }
        if(commentCreateDto.getType()==null || !CommentTypeEnum.isExist(commentCreateDto.getType())){
            return ResultDto.errorOf(ExceptionEnum.CONTENT_TYPE_ERROR);
        }
        comment.setCommentatorId(user.getId());
      //  commentMapper.insert(comment);
        commentService.createComment(comment);
        return ResultDto.errorOf(200,"回复成功");
    }
}
