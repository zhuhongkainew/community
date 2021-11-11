package life.zhk.community.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Integer id;
    private Integer sender;
    //private Integer receiver;
    private Integer type;
    private String typeName;
    private String titleName;
    private Long makeDate;
    private String sendName;
    private Integer questionId;
    private Integer status;
}
