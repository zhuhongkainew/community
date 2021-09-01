package life.zhk.community.dto;

import life.zhk.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private int creator;
    private User user;
}
