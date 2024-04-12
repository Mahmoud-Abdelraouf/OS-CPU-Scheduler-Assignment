package com.os.backend.Process;

public class PriorityProcess extends Process {
    private int priority;

    public PriorityProcess(int processNumber, int arrivalTime, int burstTime, int priority) {
        super(processNumber, arrivalTime, burstTime);
        this.priority = priority;
    }

    public PriorityProcess(int priority) {
        super();
        this.priority = priority;
    }

    public PriorityProcess(){
        this(0);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "PriorityProcess{" +
                "priority=" + priority +
                ", " +
                super.toString() +
                '}';
    }
}
