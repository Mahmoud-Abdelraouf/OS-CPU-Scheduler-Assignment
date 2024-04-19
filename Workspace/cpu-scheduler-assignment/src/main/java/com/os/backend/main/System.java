package com.os.backend.main;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessAtTime;
import com.os.frontend.schedulling_window.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class System {
    private final Backend backend;
    private final List<Observer> observersList = new ArrayList<>(4);
    private int time;
    public System(Backend backend) {
        this.backend = backend;
    }

    public void attach(Observer observer) {
        observersList.add(observer);
    }

    public void notifyObservers() {
        observersList.forEach(observer -> observer.update(this));
    }


    public Process getCurrentProcess(){
        //TODO
        return null;
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
