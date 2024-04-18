package com.os.backend.main;

import com.os.backend.Process.*;
import com.os.backend.Process.Process;
import com.os.backend.Schedule.FCFS;
import com.os.backend.Schedule.SchedulingAlgo;
import com.os.frontend.schedulling_window.observers.Observer;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class System {

    // Test block
    public static void main(String[] args) {
        Backend backend = new Backend();
        backend.setAlgo(new FCFS());

        List<Process> pList = new ArrayList<Process>();
        pList.add(new Process(1,0,3));
        pList.add(new Process(2,2,2));
        pList.add(new Process(3,3,4));
        backend.updateProcessesList(pList);
        backend.startSchedule();

    }




    private final Backend backend;
    private final List<Observer> observersList = new ArrayList<>(4);
    private int time;
    public System(Backend backend) {
        this.backend = backend;
        time = 0;
    }

    public void attach(Observer observer) {
        observersList.add(observer);
    }

    public void notifyObservers() {
        observersList.forEach(observer -> observer.update(this));
    }


    public int getCurrentProcess(ProcessTable pTable,int time){
        //TODO
        // get processExecutionEvent at time t
        List<ProcessExecutionEvent> pExecEventList = pTable.getProcessesList(time);
        //  search for running process at current time
        for(ProcessExecutionEvent e: pExecEventList){

            if( e.getState() == ProcessState.RUNNING ){
                return e.getProcessNumber();
            }
            else{
                System.out.println("No Process Running...");
            }
        }
        return -1;
    }

    public List<ProcessAtTime> getCurrentProcessesTable(){
        List<Process> processList = backend.getProcessList();
        List<ProcessAtTime> result = new ArrayList<>(processList.size());
        for(Process process : processList){
            result.add(new ProcessAtTime(process, time, backend.getTable()));
        }
        return result;
    }
}
