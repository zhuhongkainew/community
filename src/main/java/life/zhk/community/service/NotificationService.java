package life.zhk.community.service;

import life.zhk.community.dto.NotificationDto;
import life.zhk.community.dto.PaginationDto;
import life.zhk.community.dto.QuestionDto;
import life.zhk.community.mapper.NotificationMapper;
import life.zhk.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDto getPaginationListByUserId(Integer id, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount;
        Integer totalPage;
        //totalCount = questionMapper.getQuestionCountById(id);
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(id);
        totalCount = (int) notificationMapper.countByExample(example);

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
        NotificationExample example1 = new NotificationExample();
        example1.createCriteria().andReceiverEqualTo(id);
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDtos.add(notificationDto);
        }
        paginationDto.setData(notificationDtos);
        return paginationDto;
    }
}
