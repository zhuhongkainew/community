package life.zhk.community.mapper;

import life.zhk.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void createPublish(Question question);
    @Select("select count(1) from question")
    Integer getQuestionCount();
    @Select("select * from question limit #{offset},#{size}")
    List<Question> getQuestList(@Param("offset") Integer offset, @Param("size") int size);
}

