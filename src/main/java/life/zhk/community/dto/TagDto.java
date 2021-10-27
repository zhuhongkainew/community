package life.zhk.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDto {
    private String tagName;
    private List<String> tags;
}
