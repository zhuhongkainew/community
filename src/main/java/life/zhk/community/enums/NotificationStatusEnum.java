package life.zhk.community.enums;

public enum NotificationStatusEnum {
    UNREAD(0,"未读"),
    READ(1,"已读")
    ;

    private int status;
    private String statusName;

    NotificationStatusEnum(int status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }
}
