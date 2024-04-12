package com.os.backend.Schedule;

import com.os.backend.Process.Process;

import java.util.List;

public abstract class SchedulingAlgo {
    private List<Process> processesList;

    public SchedulingAlgo() {}

    public void addNewProcesses(List<Process> newProcesses){
        // TODO
    }

    public abstract void execute();

    public List<Process> getProcessesList() {
        return processesList;
    }

    public void setProcessesList(List<Process> processesList) {
        this.processesList = processesList;
    }
}
