package com.sim.wellog.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class ThreadLocalLogTrace implements LogTrace{

    private ThreadLocal<String> logTraceIdHolder = new ThreadLocal<>();

    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ThreadLocalLogTrace(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public TraceStatus start(String methodName) {
        if(Objects.isNull(logTraceIdHolder.get())) {
            logTraceIdHolder.set(UUID.randomUUID().toString());
        }
        final String logTraceId = logTraceIdHolder.get();
        final Long startTime = System.currentTimeMillis();
        return new TraceStatus(logTraceId, startTime, methodName);
    }

    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void complete(TraceStatus traceStatus, Exception exception) {
        final Long endTime = System.currentTimeMillis();
        final long resultTime = endTime - traceStatus.getStartTime();
        final LogFormat logFormat = new LogFormat(traceStatus.getTraceId(), LocalDateTime.now(), resultTime, traceStatus.getMethodName(), exception.getMessage());
        String logJson;
        try {
            logJson = objectMapper.writeValueAsString(logFormat);
        } catch (JsonProcessingException e) {
            logJson = "JsonProcessingException";
        }
        logger.info(logJson);
        logTraceIdHolder.remove();
    }
}
