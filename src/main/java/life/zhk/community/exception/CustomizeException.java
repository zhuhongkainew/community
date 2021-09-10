package life.zhk.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
    public CustomizeException(Integer code, String message) {
        this.message=message;
        this.code=code;
    }
}
