package life.zhk.community.service;

import life.zhk.community.dto.PaginationDto;
import life.zhk.community.dto.QuestionDto;
import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.Question;
import life.zhk.community.model.QuestionExample;
import life.zhk.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDto getPaginationList(Integer page,Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount;
        Integer totalPage;
        totalCount = (int)questionMapper.countByExample(new QuestionExample());
        if (totalCount / size == 0) {
            totalPage = totalCount / size;

        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDto.setPagination(totalPage, page);
        Integer offset = (page - 1) * size;
        //List<Question> questionList = questionMapper.getQuestList(offset, size);

        QuestionExample example = new QuestionExample();
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtoList(questionDtoList);
        return paginationDto;
    }

    public PaginationDto getPaginationListByUserId(Integer id, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount;
        Integer totalPage;
        //totalCount = questionMapper.getQuestionCountById(id);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        totalCount = (int)questionMapper.countByExample(example);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;

        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDto.setPagination(totalPage, page);
        Integer offset = (page - 1) * size;
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example1,new RowBounds(offset,size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.selectByPrimaryKey(question.getCreator());

            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtoList(questionDtoList);
        return paginationDto;
    }
}
