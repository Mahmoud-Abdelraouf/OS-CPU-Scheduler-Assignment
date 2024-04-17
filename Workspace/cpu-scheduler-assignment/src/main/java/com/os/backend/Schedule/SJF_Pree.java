package com.os.backend.Schedule;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessExecutionEvent;
import com.os.backend.Process.ProcessState;
import com.os.backend.Process.ProcessTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SJF_Pree extends SchedulingAlgo {
    public static void main(String[] args) {
        // Create some sample processes
        Process p1 = new Process(1, 0, 5);
        p1.setRemainingTime(4);
        Process p2 = new Process(2, 1, 3);
        p2.setRemainingTime(1);
        Process p3 = new Process(3, 2, 2);
        p3.setRemainingTime(2);
        Process p4 = new Process(4, 3, 5);

        // Add the processes to the SJF preemptive scheduling algorithm
        SJF_Pree sjfPree = new SJF_Pree();
        sjfPree.addNewProcesses(List.of(p1, p2, p3, p4));

        // Execute the SJF preemptive scheduling algorithm
        ProcessTable processTable = sjfPree.execute();

        // Output the execution events
        for (ProcessExecutionEvent event : processTable.getExecutionEvents()) {
            System.out.println(event);
        }
    }

    public SJF_Pree() {
    }

    @Override
    public ProcessTable execute() {
        ProcessTable processTable = new ProcessTable();
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstTime));
        int currentTime = 0;
        Process runningProcess = null;

        while (!processesList.isEmpty() || runningProcess != null || !readyQueue.isEmpty()) {
            List<Process> arrivedProcesses = getArrivedProcesses(currentTime);
            readyQueue.addAll(arrivedProcesses);

            if (runningProcess == null && !readyQueue.isEmpty()) {
                runningProcess = readyQueue.poll();
                processTable.addExecutionEvent(currentTime, runningProcess.getProcessNumber(), ProcessState.STARTED);
            }

            if (runningProcess != null) {
                int burstTime = runningProcess.getRemainingTime();

                // Simulate process execution
                for (int i = 0; i < burstTime; i++) {
                    int currentStatusTime = currentTime + i;

                    // Check for newly arrived processes during execution
                    List<Process> newProcesses = getArrivedProcesses(currentTime + i + 1);
                    readyQueue.addAll(newProcesses);

                    // Check if there is a shorter job in the queue
                    Process shortestJob = readyQueue.peek();
                    if (shortestJob != null && shortestJob.getBurstTime() < burstTime - i) {
                        // Interrupt the current process
                        processTable.addExecutionEvent(currentTime + i, runningProcess.getProcessNumber(), ProcessState.INTERRUPTED);
                        // Add the interrupted process back to the queue with updated remaining time
                        runningProcess.setRemainingTime(burstTime - i);
                        readyQueue.add(runningProcess);
                        // Get the new shortest job
                        runningProcess = readyQueue.poll();
                        // Add event for new process start
                        processTable.addExecutionEvent(currentTime + i, runningProcess.getProcessNumber(), ProcessState.STARTED);
                        // Update burst time for the new running process
                        burstTime = runningProcess.getRemainingTime();
                    } else {
                        // Continue running the current process
                        processTable.addExecutionEvent(currentTime + i, runningProcess.getProcessNumber(), ProcessState.RUNNING);
                    }
                }

                // Add event for process completion
                int completionTime = currentTime + burstTime;
                processTable.addExecutionEvent(completionTime, runningProcess.getProcessNumber(), ProcessState.COMPLETED);

                // Update current time
                currentTime = completionTime;

                // Remove the completed process from the list
                processesList.remove(runningProcess);
                runningProcess = null;
            } else {
                currentTime++;
            }
        }

        return processTable;
    }

    // Helper method to get arrived processes at a given time
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