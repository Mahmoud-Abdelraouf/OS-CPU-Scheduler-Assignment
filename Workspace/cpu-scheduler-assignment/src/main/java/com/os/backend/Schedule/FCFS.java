package com.os.backend.Schedule;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessExecutionEvent;
import com.os.backend.Process.ProcessState;
import com.os.backend.Process.ProcessTable;

import java.util.ArrayList;
import java.util.List;

public class FCFS extends SchedulingAlgo {
    public FCFS() {
    }

    @Override
    public ProcessTable execute() {
        List<ProcessExecutionEvent> executionEvents = new ArrayList<>();
        int currentTime = 0;

        while (true) {
            // Check if there are any new processes arrived at the current time
            List<Process> arrivedProcesses = getArrivedProcesses(currentTime);

            // Execute the arrived processes
            for (Process process : arrivedProcesses) {
                // Add event for process arrival
                executionEvents.add(new ProcessExecutionEvent(currentTime, process.getProcessNumber(), ProcessState.ARRIVED));

                // Execute the process
                executeProcess(process, executionEvents, currentTime);

                // Update current time
                currentTime += process.getBurstTime();
            }

            // Check if there are no more processes remaining
            if (processesList.isEmpty()) {
                break; // Exit the loop if there are no more processes
            }

            // Increment current time to check the next time unit
            currentTime++;
        }

        return new ProcessTable(executionEvents);
    }

    private List<Process> getArrivedProcesses(int currentTime) {
        List<Process> arrivedProcesses = new ArrayList<>();
        processesList.removeIf(process -> process.getArrivalTime() < currentTime);
        processesList.forEach(process -> {
            if (process.getArrivalTime() <= currentTime) {
                arrivedProcesses.add(process);
            }
        });
        return arrivedProcesses;
    }

    private void executeProcess(Process process, List<ProcessExecutionEvent> executionEvents, int startTime) {
        // Add event for process start
        executionEvents.add(new ProcessExecutionEvent(startTime, process.getProcessNumber(), ProcessState.STARTED));

        // Simulate process execution
        int endTime = startTime + process.getBurstTime();
        for (int i = startTime + 1; i <= endTime; i++) {
            executionEvents.add(new ProcessExecutionEvent(i, process.getProcessNumber(), ProcessState.RUNNING));
        }

        // Add event for process completion
        executionEvents.add(new ProcessExecutionEvent(endTime, process.getProcessNumber(), ProcessState.COMPLETED));
    }
}
