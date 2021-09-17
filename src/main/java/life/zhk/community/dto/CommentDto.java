package life.zhk.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Integer parentId;
    private String Content;
    private Integer type;
}
