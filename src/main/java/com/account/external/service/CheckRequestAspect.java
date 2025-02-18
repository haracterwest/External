package com.account.external.service;

import com.account.external.model.ExternalInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(CheckRequestAspect.class);

    @Around("@annotation(CheckRequest)")
    public Object checkRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof ExternalInfo) {
                ExternalInfo externalInfo = (ExternalInfo) arg;
                if (externalInfo.getId() == 3) { // id-not-process
                    logger.info("Запрос с id " + externalInfo.getId() + " не прошел проверку.");
                    return null; // Прерываем выполнение метода
                }
            }
        }
        return joinPoint.proceed();
    }
}
