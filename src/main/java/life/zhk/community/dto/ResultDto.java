package life.zhk.community.dto;

import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import lombok.Data;

@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;

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

    public static Object isOk(int i, String ddd) {
        return errorOf(i,ddd);
    }

    public static <T> ResultDto okOf(T t) {
        ResultDto resultDTO = new ResultDto();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}
