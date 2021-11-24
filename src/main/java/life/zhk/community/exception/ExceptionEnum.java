package life.zhk.community.exception;

public enum ExceptionEnum {
    QUESTION_NOT_FOUND(1,"问题不存在，请检查"),
    COMMENT_NOT_FOUND(2,"评论不存在，请检查"),
    USER_NO_LOGIN(4001,"用户未登录，请登录后重试"),
    SYS_ERROR(4000,"服务器冒烟了。。。请稍后再试"),
    CONTENT_EMPTY(4002,"评论内容不能为空"),
    CONTENT_TYPE_ERROR(4003,"评论类型有误")

    ;


    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
