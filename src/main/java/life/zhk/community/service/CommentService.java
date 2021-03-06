package life.zhk.community.service;

import life.zhk.community.dto.CommentDto;
import life.zhk.community.enums.CommentTypeEnum;
import life.zhk.community.enums.NotificationEnum;


import life.zhk.community.enums.NotificationStatusEnum;
import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.mapper.*;
import life.zhk.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private CommentEXMapper commentEXMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void createComment(Comment comment, String name) {

        if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionEXMapper.incComment(question);
            createNotification(question.getId(),name,question.getTitle(),comment.getCommentatorId(),question.getCreator(),NotificationEnum.NOTIFICATION_QUESTION);
        } else {
            //回复评论-二级评论
            Comment parentComment =commentMapper.selectByPrimaryKey((long)comment.getParentId());
            if (parentComment == null) {
                throw new CustomizeException(ExceptionEnum.COMMENT_NOT_FOUND);
            }
            Question question = questionMapper.selectByPrimaryKey(parentComment.getParentId());
            if (question == null) {
                throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加评论次数
            Comment commentParent = new Comment();
            commentParent.setId(comment.getParentId().longValue());
            commentParent.setCommentCount(1L);
            commentEXMapper.incComment(commentParent);

            createNotification(question.getId(),name,question.getTitle(),comment.getCommentatorId(),parentComment.getCommentatorId(), NotificationEnum.NOTIFICATION_COMMENT);

        }

    }
    public  void  createNotification(int outId, String name, String title, Integer commentatorId, Integer receivedId, NotificationEnum notificationQuestion){
        Notification notification =new Notification();
        notification.setMakeDate(System.currentTimeMillis());
        notification.setQuestionId(outId);
        notification.setReceiver(receivedId);
        notification.setSender(commentatorId);
        notification.setSendName(name);
        notification.setTitleName(title);
        notification.setType(notificationQuestion.getType());
        notification.setTypeName(notificationQuestion.getName());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notificationMapper.insert(notification);
    }
    public List<CommentDto> getCommentByParentId(Integer id, CommentTypeEnum type) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //Lambda将comment转换为评论用户id集合
        List<Integer> collect = comments.stream().map(c -> c.getCommentatorId()).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(collect);
        List<User> userList = userMapper.selectByExample(userExample);
        //根据id查到的用户集合用lambda表达式转换为map方便后续获取
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //用Lambda表达式将commentList转换为commentDto,同时去上面User转换的map里获取user信息，赋值到commentDto
        List<CommentDto> CommentDto1 = comments.stream().map(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);
            commentDto.setUser(userMap.get(commentDto.getCommentatorId()));
            return commentDto;
        }).collect(Collectors.toList());
        return CommentDto1;
    }
}
