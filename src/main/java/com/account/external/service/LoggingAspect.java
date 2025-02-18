package com.account.external.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.account.external.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Start: " + joinPoint.getSignature());
        try {
            Object result = joinPoint.proceed();
            logger.info("End: " + joinPoint.getSignature());
            return result;
        } catch (Throwable throwable) {
            logger.error("Error in method: " + joinPoint.getSignature() + ", Error: " + throwable.getMessage());
            throw throwable;
        }
    }
}
