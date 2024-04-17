package com.os.backend.Schedule;

import com.os.backend.Process.ProcessExecutionEvent;
import com.os.backend.Process.ProcessTable;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends SchedulingAlgo{
    private final int timeQuantum;
    public RoundRobin(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public ProcessTable execute() {
        //TODO
        return new ProcessTable();
    }
 public int getTimeQuantum() {
        return timeQuantum;
 }
}
