package life.zhk.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ExceptionEnum exceptionEnum) {
        this.message=exceptionEnum.getMessage();
        this.code=exceptionEnum.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

}
