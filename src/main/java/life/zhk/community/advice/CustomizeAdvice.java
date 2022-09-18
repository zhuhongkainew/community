package life.zhk.community.advice;

import com.alibaba.fastjson.JSONObject;
import life.zhk.community.dto.ResultDto;
import life.zhk.community.exception.CustomizeException;
import life.zhk.community.exception.ExceptionEnum;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//测试
@ControllerAdvice
public class CustomizeAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Throwable ex, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
      String contentType= request.getContentType();
        if("application/json".equals(contentType)){
           ResultDto resultDto;
           if( ex instanceof CustomizeException){
               resultDto=ResultDto.errorOf((CustomizeException)ex);
               //response.w
               //model.addAttribute("message", ex.getMessage());
           }else {
               //model.addAttribute("message", "服务器异常了，请稍后再试");
               resultDto=ResultDto.errorOf(ExceptionEnum.SYS_ERROR);
           }
           response.setContentType("application/json");
           response.setCharacterEncoding("UTF-8");
           PrintWriter writer = response.getWriter();
           writer.write(JSONObject.toJSONString(resultDto));
           writer.close();
           return null;
       }else {
           if (ex instanceof CustomizeException) {
               model.addAttribute("message", ex.getMessage());
           } else {
               model.addAttribute(ExceptionEnum.SYS_ERROR.getMessage());
           }
           return new ModelAndView("error");
       }
    }
}
