package com.os.backend.Process;

import java.util.List;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProcessTable {
    private final List<ProcessExecutionEvent> executionEvents;

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

    public List<ProcessExecutionEvent> getProcessesList(int time){
        return executionEvents.stream()
                .filter(e -> e.getTime() == time)
                .collect(Collectors.toList());
    }

}