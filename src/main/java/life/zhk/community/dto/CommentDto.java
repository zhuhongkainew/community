package life.zhk.community.dto;

import life.zhk.community.model.User;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Integer parentId;
    private Integer type;
    private String content;
    private Long likeCount;
    private Integer commentatorId;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
    private Long commentCount;
}
