package com.example.accountservice.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author m-sabbaghi
 * <a href="https://www.linkedin.com/in/sabbaghi/">...</a>
 * @version 6/14/2022
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class DuplicationInterceptor {
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    public DuplicationInterceptor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    @Around(value = "@annotation(com.example.accountservice.interceptor.PreventDuplication)")
    public Object duplicationHandler(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method methodAnnotated = methodSignature.getMethod();
        return null;
    }
}
