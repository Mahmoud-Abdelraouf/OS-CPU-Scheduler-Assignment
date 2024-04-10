package com.os.backend.main;

import com.os.backend.Process.Process;
import com.os.frontend.schedulling_window.observers.Observer;

import java.util.List;

public class System {
    List<Observer> observersList;

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

    public List<Process> getCurrentProcessesTable(){
        //TODO
        return null;
    }
}
