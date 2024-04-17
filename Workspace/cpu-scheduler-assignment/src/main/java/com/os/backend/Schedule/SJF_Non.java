package com.os.backend.Schedule;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessExecutionEvent;
import com.os.backend.Process.ProcessState;
import com.os.backend.Process.ProcessTable;

import java.util.ArrayList;
import java.util.List;

public class SJF_Non extends SchedulingAlgo {
    public static void main(String[] args) {
        // Create SJF_Non instance
        SJF_Non sjf = new SJF_Non();

        // Create sample processes
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 5));
        processes.add(new Process(2, 2, 8));
        processes.add(new Process(3, 4, 3));

        // Add processes to the scheduler
        sjf.addNewProcesses(processes);

        // Execute the SJF non-preemptive algorithm
        ProcessTable processTable = sjf.execute();

        // Print each event on a new line
        for (ProcessExecutionEvent event : processTable.getExecutionEvents()) {
            System.out.println(event);
        }
    }

    public SJF_Non() {
    }

    @Override
    public ProcessTable execute() {
        ProcessTable processTable = new ProcessTable();
        int currentTime = 0;

        while (!processesList.isEmpty()) { // Continue until all processes are executed
            // Get the processes that have arrived by the current time
            List<Process> arrivedProcesses = getArrivedProcesses(currentTime);

            if (arrivedProcesses.isEmpty()) {
                // If no processes have arrived, increment current time
                currentTime++;
                continue;
            }

            // Sort arrived processes by burst time (shortest job first)
            arrivedProcesses.sort((p1, p2) -> Integer.compare(p1.getBurstTime(), p2.getBurstTime()));

            // Get the process with the shortest burst time
            Process shortestProcess = arrivedProcesses.get(0);

            // Add event for process arrival
            processTable.addExecutionEvent(currentTime, shortestProcess.getProcessNumber(), ProcessState.ARRIVED);

            // Add event for process start
            processTable.addExecutionEvent(currentTime, shortestProcess.getProcessNumber(), ProcessState.STARTED);

            // Simulate process execution
            int endTime = currentTime + shortestProcess.getBurstTime();
            for (int i = currentTime + 1; i <= endTime; i++) {
                processTable.addExecutionEvent(i, shortestProcess.getProcessNumber(), ProcessState.RUNNING);
            }

            // Add event for process completion
            processTable.addExecutionEvent(endTime, shortestProcess.getProcessNumber(), ProcessState.COMPLETED);

            // Update current time
            currentTime = endTime;

            // Remove the executed process from the list
            processesList.remove(shortestProcess);
        }

        return processTable;
    }

    private List<Process> getArrivedProcesses(int currentTime) {
        List<Process> arrivedProcesses = new ArrayList<>();
        for (Process process : processesList) {
            if (process.getArrivalTime() <= currentTime) {
                arrivedProcesses.add(process);
            }
        }
        return arrivedProcesses;
    }
}
