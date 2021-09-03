package life.zhk.community.service;

import life.zhk.community.dto.PaginationDto;
import life.zhk.community.dto.QuestionDto;
import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.Question;
import life.zhk.community.model.User;
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
        totalCount = questionMapper.getQuestionCount();
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
        List<Question> questionList = questionMapper.getQuestList(offset, size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.findById(question.getCreator());
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtoList(questionDtoList);
        return paginationDto;
    }
}
