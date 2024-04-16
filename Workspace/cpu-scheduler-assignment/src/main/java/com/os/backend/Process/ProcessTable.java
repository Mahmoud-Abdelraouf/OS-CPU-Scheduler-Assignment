package com.os.backend.Process;

import java.util.List;

public class ProcessTable {
    private List<ProcessExecutionEvent> executionEvents;

    public ProcessTable(List<ProcessExecutionEvent> executionEvents) {
        this.executionEvents = executionEvents;
    }

    public List<ProcessExecutionEvent> getExecutionEvents() {
        return executionEvents;
    }

    public void setExecutionEvents(List<ProcessExecutionEvent> executionEvents) {
        this.executionEvents = executionEvents;
    }

    @Override
    public String toString() {
        return "ProcessTable{" + "executionEvents=" + executionEvents + '}';
    }
}
