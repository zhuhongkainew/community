package life.zhk.community.model;

import lombok.Data;

@Data
public class Question {
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
}
