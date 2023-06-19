package com.sim.wellog.log;

import java.time.LocalDateTime;

public class LogFormat {

    private String logId;
    private LocalDateTime logDate;
    private long logTime;
    private String logMethod;
    private String exceptionTrace;


    public LogFormat(String logId, LocalDateTime logDate, long logTime, String logMethod, String exceptionTrace) {
        this.logId = logId;
        this.logDate = logDate;
        this.logTime = logTime;
        this.logMethod = logMethod;
        this.exceptionTrace = exceptionTrace;
    }

    public String getLogId() {
        return logId;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public long getLogTime() {
        return logTime;
    }

    public String getLogMethod() {
        return logMethod;
    }

    public String getExceptionTrace() {
        return exceptionTrace;
    }
}
