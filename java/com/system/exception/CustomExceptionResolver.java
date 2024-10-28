package com.system.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 *    全局异常处理器
 *    springmvc提供一个HandlerExceptionResolver接口
 *      只要实现该接口，并配置到spring 容器里，该类就能
 *      成为默认全局异常处理类
 *
 *   全局异常处理器只有一个，配置多个也没用。
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
//        try {
//            httpServletRequest.setCharacterEncoding("utf-8");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//        httpServletResponse.setHeader("Content-type", "text/plain;charset=UTF-8");
//        httpServletResponse.setCharacterEncoding("utf-8");
        CustomException customException;
        if (e instanceof CustomException) {

            modelAndView.addObject("message", e.getMessage());

        } else if (e instanceof UnknownAccountException) {
            //用户名错误异常
            modelAndView.addObject("message", "用户名不存在");

        } else if (e instanceof IncorrectCredentialsException) {
            //用户名错误异常
            modelAndView.addObject("message", "密码错误");

        } else {
            modelAndView.addObject("message", "您的操作有误，请看编译器后台异常信息");

        }



        Object message1 = modelAndView.getModel().get("message");
        //错误信息传递和错误页面跳转
        try {
            modelAndView.addObject("message", message1.toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        modelAndView.setViewName("error");


        return modelAndView;
    }
}
