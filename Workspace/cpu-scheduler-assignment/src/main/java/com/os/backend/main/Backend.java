package com.os.backend.main;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessTable;
import com.os.backend.Schedule.SchedulingAlgo;
import com.os.frontend.scheduling_window.observers.Observer;

import java.util.List;

public class Backend {

    private SchedulingAlgo scheduler;
    private SystemScheduler system;
    private List<Process> processList;
    private ProcessTable table;
    private int time;

    public Backend(){
        this.system = new SystemScheduler(this);
        time = 0;
    }

    public void setAlgo(SchedulingAlgo scheduler){
        this.scheduler = scheduler;
        //TODO
    }

    public void startSchedule(){
        //TODO: Start timer
        // Check for empty ProcessTable
        if( this.table.getExecutionEvents().isEmpty()) return;

        // Start processing from time t=0
        // checking every 1 sec for running process
        Thread processing = new Thread(this.system);
        processing.start();
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

    public SchedulingAlgo getScheduler() {
        return scheduler;
    }
}
