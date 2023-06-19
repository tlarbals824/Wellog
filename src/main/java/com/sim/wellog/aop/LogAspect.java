package com.sim.wellog.aop;

import com.sim.wellog.log.LogTrace;
import com.sim.wellog.log.TraceStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    private final LogTrace logTrace;

    public LogAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("@annotation(com.sim.wellog.log.Wellog)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus start = logTrace.start(joinPoint.getSignature().getName());
        try {
            Object proceed = joinPoint.proceed();
            logTrace.end(start);
            return proceed;
        } catch (Exception e) {
            logTrace.exception(start,e);
            throw e;
        }

    }
}
