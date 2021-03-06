package life.zhk.community.service;

import life.zhk.community.dto.PaginationDto;
import life.zhk.community.dto.QuestionDto;
import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import life.zhk.community.mapper.QuestionEXMapper;
import life.zhk.community.mapper.QuestionMapper;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.Question;
import life.zhk.community.model.QuestionExample;
import life.zhk.community.model.User;
import life.zhk.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionEXMapper questionEXMapper;

    public PaginationDto getPaginationList(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount;
        Integer totalPage;
        totalCount = (int) questionMapper.countByExample(new QuestionExample());
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
        //List<Question> questionList = questionMapper.getQuestList(offset, size);

        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_Create desc");
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
       // List<QuestionDto> questionDtoList = new ArrayList<>();
//        for (Question question : questionList) {
//            QuestionDto questionDto = new QuestionDto();
//            BeanUtils.copyProperties(question, questionDto);
//            User user = userMapper.selectByPrimaryKey(question.getCreator());
//            questionDto.setUser(user);
//            questionDtoList.add(questionDto);
//        }
        List<Integer> userIdList = questionList.stream().map(question -> question.getCreator()).distinct().collect(Collectors.toList());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIdList);
        List<User> userList = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<QuestionDto> questionDtoList = questionList.stream().map(question -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(userMap.get(questionDto.getCreator()));
            return questionDto;
        }).collect(Collectors.toList());
        paginationDto.setData(questionDtoList);
        return paginationDto;
    }

    public PaginationDto getPaginationListByUserId(Integer id, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount;
        Integer totalPage;
        //totalCount = questionMapper.getQuestionCountById(id);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        totalCount = (int) questionMapper.countByExample(example);

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
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.selectByPrimaryKey(question.getCreator());

            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setData(questionDtoList);
        return paginationDto;
    }

    public QuestionDto getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question==null){
          throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    public void createdOrUpdateQuestion(Question question) {
        if (question.getId() == null) {

            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            Question questionUpdate = new Question();
            questionUpdate.setDescription(question.getDescription());
            questionUpdate.setTag(question.getTag());
            questionUpdate.setTitle(question.getTitle());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(questionUpdate, example);
        }
    }

    public void incView(Integer id) {
        Question question=new Question();
        question.setId(id);
        question.setViewCount(1);
        questionEXMapper.incView(question);
    }

    public List<Question> getRelationQuestion(QuestionDto questionDto) {
        String[] tags = questionDto.getTag().split(",");
        String tag = Arrays.asList(tags).stream().collect(Collectors.joining("|"));
        Question question =new Question();
        question.setId(questionDto.getId());
        question.setTag(tag);
        List<Question> questionList =questionEXMapper.selectRelation(question);
        return questionList;
    }
}
