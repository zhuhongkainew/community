package life.zhk.community.service;

import life.zhk.community.mapper.CommentMapper;
import life.zhk.community.mapper.QuestionEXMapper;
import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionEXMapper questionEXMapper;

    public void createComment(Comment comment) {

    }
}
