package com.os.backend.main;

import com.os.backend.Process.Process;
import com.os.backend.Schedule.SchedulingAlgo;
import com.os.frontend.schedulling_window.observers.Observer;

import java.util.List;

public class Backend {
    private SchedulingAlgo scheduler;
    private System system;
    private List<Process> processList;

    public void setAlgo(SchedulingAlgo scheduler){
        this.scheduler = scheduler;
        //TODO
    }

    public void startSchedule(){
        //TODO
        scheduler.execute();
        system.notify();
    }

    public void updateProcessesList(List<Process> newProcesses){
        this.processList = newProcesses;
        //TODO
        system.notify();
        scheduler.addNewProcesses(this.processList);
    }

    //For the observer
    public void attach(Observer observer){
        system.attach(observer);
    }
}
