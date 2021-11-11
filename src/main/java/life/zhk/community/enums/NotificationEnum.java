package life.zhk.community.enums;

public enum NotificationEnum {
    NOTIFICATION_QUESTION(1,"回复了问题"),
    NOTIFICATION_COMMENT(2,"回复了评论")
    ;
    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
