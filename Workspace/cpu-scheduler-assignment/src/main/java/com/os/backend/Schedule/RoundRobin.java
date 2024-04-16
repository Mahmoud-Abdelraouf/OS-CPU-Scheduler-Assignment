package com.os.backend.Schedule;

import com.os.backend.Process.ProcessExecutionEvent;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends SchedulingAlgo{
    private final int timeQuantum;
    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public List<ProcessExecutionEvent> execute() {
        //TODO
        return new ArrayList<ProcessExecutionEvent>();
    }
}
