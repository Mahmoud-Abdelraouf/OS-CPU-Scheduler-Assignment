package com.os.backend.main;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessTable;
import com.os.backend.Schedule.SchedulingAlgo;
import com.os.frontend.schedulling_window.observers.Observer;
import com.os.frontend.schedulling_window.observers.ProcessesTable;

import java.util.List;

public class Backend {
    private SchedulingAlgo scheduler;
    private System system;
    private List<Process> processList;
    private ProcessTable table;

    public Backend(){
        this.system = new System(this);
    }

    public void setAlgo(SchedulingAlgo scheduler){
        this.scheduler = scheduler;
        //TODO
    }

    public void startSchedule(){
        //TODO: Start timer
        system.notifyObservers();
    }

    public void updateProcessesList(List<Process> newProcesses){
        this.processList = newProcesses;
        //TODO
        scheduler.addNewProcesses(this.processList);
        this.table = scheduler.execute();
    }

    //For the observer
    public void attach(Observer observer){
        system.attach(observer);
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public ProcessTable getTable() {
        return table;
    }
}
