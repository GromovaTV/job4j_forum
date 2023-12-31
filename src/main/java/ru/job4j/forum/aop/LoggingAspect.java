package ru.job4j.forum.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* ru.job4j.forum.control.PostControl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Logging before " + joinPoint.getSignature().getName());
    }
}