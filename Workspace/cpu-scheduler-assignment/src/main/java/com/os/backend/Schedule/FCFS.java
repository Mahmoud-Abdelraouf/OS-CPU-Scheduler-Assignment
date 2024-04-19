package com.os.backend.Schedule;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessExecutionEvent;
import com.os.backend.Process.ProcessState;
import com.os.backend.Process.ProcessTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Iterator;

public class FCFS extends SchedulingAlgo {

    public static void main(String[] args) {
        // Create some sample processes
        Process p1 = new Process(1, 0, 5);
        Process p2 = new Process(2, 1, 3);
        Process p3 = new Process(3, 2, 2);
        Process p4 = new Process(4, 3, 5);

        // Add the processes to a list
        List<Process> processesList = new ArrayList<>(List.of(p1, p2, p3, p4));

        // Create an instance of your scheduling algorithm
        FCFS fcfs = new FCFS();

        // Set the list of processes for the algorithm to execute
        fcfs.addNewProcesses(processesList);

        // Execute the algorithm
        ProcessTable processTable = fcfs.execute();

        // Output the execution events
        for (ProcessExecutionEvent event : processTable.getExecutionEvents()) {
            System.out.println(event);
        }
    }

    public FCFS() {
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

            // Get the process that arrived first (FCFS)
            Process firstProcess = arrivedProcesses.get(0);

            // Add event for process arrival
            processTable.addExecutionEvent(firstProcess, currentTime, firstProcess.getProcessNumber(), ProcessState.ARRIVED);

            // Add event for process start
            processTable.addExecutionEvent(firstProcess, currentTime, firstProcess.getProcessNumber(), ProcessState.STARTED);

            // Simulate process execution
            int endTime = currentTime + firstProcess.getBurstTime();
            for (int i = currentTime + 1; i <= endTime; i++) {
                processTable.addExecutionEvent(firstProcess, i, firstProcess.getProcessNumber(), ProcessState.RUNNING);
            }

            // Add event for process completion
            processTable.addExecutionEvent(firstProcess, endTime, firstProcess.getProcessNumber(), ProcessState.COMPLETED);

            // Update current time
            currentTime = endTime;

            // Remove the executed process from the list
            processesList.remove(firstProcess);
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