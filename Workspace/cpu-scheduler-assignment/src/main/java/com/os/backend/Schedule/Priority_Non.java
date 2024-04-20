package com.os.backend.Schedule;

import com.os.backend.Process.*;
import com.os.backend.Process.Process;

import java.util.ArrayList;
import java.util.List;

public class Priority_Non extends SchedulingAlgo {

    public Priority_Non() {
    }

    @Override
    public ProcessTable execute() {

        ProcessTable processTable = new ProcessTable();

        int currentTime = 0;
        while (!processesList.isEmpty()) { // Continue until all processes are executed
            // Sort the processes by arrival time (assuming lower arrival time means higher priority)
            processesList.sort((p1, p2) -> {
                if (p1.getArrivalTime() != p2.getArrivalTime()) {
                    return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
                } else {
                    // If arrival times are equal, prioritize based on priority
                    if (p1 instanceof PriorityProcess && p2 instanceof PriorityProcess) {
                        return Integer.compare(((PriorityProcess) p1).getPriority(), ((PriorityProcess) p2).getPriority());
                    } else {
                        return 0; // Handle case where either p1 or p2 is not an instance of PriorityProcess
                    }
                }
            });

            // Get the next process to execute
            Process process = processesList.get(0);

            // If the process has not arrived yet, update the current time
            if (process.getArrivalTime() > currentTime) {
                currentTime = process.getArrivalTime();
            }

            // Add event for process arrival
            processTable.addExecutionEvent(process, currentTime, process.getProcessNumber(), ProcessState.ARRIVED);

            // Add event for process start
            processTable.addExecutionEvent(process, currentTime, process.getProcessNumber(), ProcessState.STARTED);

            // Simulate process execution
            int endTime = currentTime + process.getBurstTime();
            for (int i = currentTime + 1; i <= endTime; i++) {
                processTable.addExecutionEvent(process, i, process.getProcessNumber(), ProcessState.RUNNING);
            }

            // Add event for process completion
            processTable.addExecutionEvent(process, endTime, process.getProcessNumber(), ProcessState.COMPLETED);

            // Update current time
            currentTime = endTime;

            // Remove the executed process from the list
            processesList.remove(process);
        }


        return processTable;

    }

    @Override
    public String getSchedulerName() {
        return "Non-Preemptive Priority";
    }
}
