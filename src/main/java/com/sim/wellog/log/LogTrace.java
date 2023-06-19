package com.sim.wellog.log;

public interface LogTrace {

    TraceStatus start(String methodName);
    void end(TraceStatus traceStatus);
    void exception(TraceStatus traceStatus, Exception e);

}
