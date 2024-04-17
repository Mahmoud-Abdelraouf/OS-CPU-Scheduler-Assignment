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
        Process p2 = new Process(2, 1, 3);
        Process p3 = new Process(3, 2, 2);
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
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingTime).thenComparing(Process::getArrivalTime));
        int currentTime = 0;

        while (true) {
            // Check if there are any new processes arrived at the current time
            List<Process> arrivedProcesses = getArrivedProcesses(currentTime);
            readyQueue.addAll(arrivedProcesses);

            // If no process is running and the ready queue is not empty, start the shortest job
            if (readyQueue.isEmpty()) {
                if (!processesList.isEmpty()) {
                    currentTime = processesList.get(0).getArrivalTime();
                    continue;
                } else {
                    break; // Exit the loop if there are no more processes
                }
            }

            // Get the shortest job from the ready queue
            Process runningProcess = readyQueue.poll();
            int burstTime = runningProcess.getRemainingTime(); // Remaining time of the running process

            // Add event for process start
            processTable.addExecutionEvent(currentTime, runningProcess.getProcessNumber(), ProcessState.STARTED);

            // Simulate process execution and track the status of each process for each unit of time
            for (int i = 0; i < burstTime; i++) {
                int currentStatusTime = currentTime + i;

                // Check if any new processes have arrived during execution
                List<Process> newProcesses = getArrivedProcesses(currentTime + i + 1);
                readyQueue.addAll(newProcesses);

                // If there is a shorter job in the ready queue, preempt the current process
                Process shortestJob = readyQueue.peek();
                if (shortestJob != null && shortestJob.getRemainingTime() < burstTime - i) {
                    // Add event for process interruption
                    processTable.addExecutionEvent(currentTime + i, runningProcess.getProcessNumber(), ProcessState.INTERRUPTED);
                    // Update remaining time for the interrupted process
                    runningProcess.setRemainingTime(burstTime - i);
                    readyQueue.add(runningProcess);
                    // Get the new shortest job
                    runningProcess = readyQueue.poll();
                    // Add event for new process start
                    processTable.addExecutionEvent(currentTime + i, runningProcess.getProcessNumber(), ProcessState.STARTED);
                    // Update burst time for the new running process
                    burstTime = runningProcess.getRemainingTime();
                } else {
                    // Add event for process running
                    processTable.addExecutionEvent(currentStatusTime, runningProcess.getProcessNumber(), ProcessState.RUNNING);
                }
            }

            // Add event for process completion
            int completionTime = currentTime + runningProcess.getBurstTime();
            processTable.addExecutionEvent(completionTime, runningProcess.getProcessNumber(), ProcessState.COMPLETED);

            // Update current time
            currentTime = completionTime;

            // Remove the completed process from the processes list
            processesList.remove(runningProcess);

            // If there are no more processes, exit the loop
            if (processesList.isEmpty() && readyQueue.isEmpty()) {
                break;
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
