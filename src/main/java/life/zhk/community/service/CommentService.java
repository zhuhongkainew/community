package life.zhk.community.service;

import life.zhk.community.dto.CommentDto;
import life.zhk.community.enums.CommentTypeEnum;
import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.mapper.CommentMapper;
import life.zhk.community.mapper.QuestionEXMapper;
import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionEXMapper questionEXMapper;
    @Autowired
    private UserMapper userMapper;
@Transactional
    public void createComment(Comment comment) {

    if(comment.getType() == CommentTypeEnum.QUESTION.getType()){
    //回复问题
        Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
        if(question==null){
           throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
        }
        commentMapper.insert(comment);
        question.setCommentCount(1);
        questionEXMapper.incComment(question);
    }else{
        //回复评论-二级评论
    }

    }

    public List<CommentDto> getCommentByParentId(Integer id, Integer type) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<Integer> collect = comments.stream().map(c -> c.getCommentatorId()).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(collect);
        List<User> userList =userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDto> CommentDto1 = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(commentDto.getCommentatorId()));
            return commentDto;
        }).collect(Collectors.toList());
        return CommentDto1;
    }
}