package com.os.backend.Process;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class ProcessTable {
    private List<ProcessExecutionEvent> executionEvents;

    public ProcessTable() {
        this.executionEvents = new ArrayList<>();
    }

    public void addExecutionEvent(int time, int processNumber, ProcessState state) {
        this.executionEvents.add(new ProcessExecutionEvent(time, processNumber, state));
    }

    public List<ProcessExecutionEvent> getExecutionEvents() {
        return executionEvents;
    }

    @Override
    public String toString() {
        return "ProcessTable{" + "executionEvents=" + executionEvents + '}';
    }
}