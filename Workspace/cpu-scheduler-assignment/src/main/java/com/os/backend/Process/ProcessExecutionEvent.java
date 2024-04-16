package com.os.backend.Process;

public class ProcessExecutionEvent {
    private int time;
    private int processNumber;
    private ProcessState state;

    public ProcessExecutionEvent(int time, int processNumber, ProcessState state) {
        this.time = time;
        this.processNumber = processNumber;
        this.state = state;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ProcessExecutionEvent{" +
                "time=" + time +
                ", processNumber=" + processNumber +
                ", state=" + state +
                '}';
    }
}
