package life.zhk.community.exception;

public enum ExceptionEnum {
    QUESTION_NOT_FOUND(1,"问题不存在，请检查"),
    USER_NO_LOGIN(4001,"用户未登录，请登录后重试"),
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
