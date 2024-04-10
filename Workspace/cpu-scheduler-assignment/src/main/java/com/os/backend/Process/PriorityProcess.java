package com.os.backend.Process;

public class PriorityProcess extends Process {
    private int priority;

    public PriorityProcess(int arrivalTime, int burstTime, int priority) {
        super(arrivalTime, burstTime);
        this.priority = priority;
    }

    public PriorityProcess(int priority) {
        super();
        this.priority = priority;
    }

    public PriorityProcess(){
        this(0);
    }

    public int priority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
