package com.xiaxinyu.myblog.hander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;


@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    //ModelAndView返回值为视图
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception{

        //判断是否存在状态标识，如果不存在则抛出异常
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(),e);
        //在控制台打印错误堆栈信息
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        //将异常url和异常类型传递给视图，可在error.html的源代码中以注释的形式显示异常信息
        mv.setViewName("error/error");
        return mv;
    }
}
