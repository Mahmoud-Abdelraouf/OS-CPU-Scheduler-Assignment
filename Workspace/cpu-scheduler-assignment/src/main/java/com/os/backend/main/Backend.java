package com.os.backend.main;

import com.os.backend.Schedule.SchedulingAlgo;
import com.os.frontend.schedulling_window.observers.Observer;

import java.util.List;

public class Backend {
    private SchedulingAlgo scheduler;
    private System system;

    public void setAlgo(SchedulingAlgo scheduler){
        //TODO
    }

    public void startSchedule(){
        //TODO
    }

    public void updateProcessesList(List<Process> newProcesses){
        //TODO
    }

    //For the observer
    public void attach(Observer observer){
        system.attach(observer);
    }
}
