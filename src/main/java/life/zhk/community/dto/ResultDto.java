package life.zhk.community.dto;

import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import lombok.Data;

@Data
public class ResultDto {
    private Integer code;
    private String message;

    public static  ResultDto errorOf(Integer code, String message){
        ResultDto resultDto =new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(ExceptionEnum exceptionEnum) {
        return errorOf(exceptionEnum.getCode(),exceptionEnum.getMessage());
    }

    public static ResultDto errorOf(CustomizeException ex) {
        return  errorOf(ex.getCode(),ex.getMessage());
    }
}
