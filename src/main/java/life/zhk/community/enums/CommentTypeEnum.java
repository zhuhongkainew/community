package life.zhk.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2)
    ;
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
    public static   Boolean isExist(Integer code){
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(code==commentTypeEnum.type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
