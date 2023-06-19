package com.sim.wellog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sim.wellog.aop.LogAspect;
import com.sim.wellog.log.LogTrace;
import com.sim.wellog.log.ThreadLocalLogTrace;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(name = "wellog", havingValue = "on")
public class LogConfig {

    @Bean
    public LogAspect logAspect(LogTrace logTrace) {
        return new LogAspect(logTrace);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace(new ObjectMapper());
    }
}
