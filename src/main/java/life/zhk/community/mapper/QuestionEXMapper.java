package life.zhk.community.mapper;

import life.zhk.community.model.Question;

import java.util.List;

public interface QuestionEXMapper {
    int incView(Question record);
    int incComment(Question record);
    List<Question> selectRelation(Question record);
}