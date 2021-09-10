package life.zhk.community.advice;

import life.zhk.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(Throwable ex, Model model,HttpServletRequest request) {
       if( ex instanceof CustomizeException){
           model.addAttribute("message", ex.getMessage());
        }else {
           model.addAttribute("message", "服务器异常了，请稍后再试");
       }
        return new ModelAndView("error");
    }
}
