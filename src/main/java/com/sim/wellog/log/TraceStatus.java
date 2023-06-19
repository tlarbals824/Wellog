package com.sim.wellog.log;

public class TraceStatus {

    private String traceId;
    private Long startTime;
    private String methodName;

    public TraceStatus(String traceId, Long startTime, String methodName) {
        this.traceId = traceId;
        this.startTime = startTime;
        this.methodName = methodName;
    }

    public String getTraceId() {
        return traceId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public String getMethodName() {
        return methodName;
    }
}
