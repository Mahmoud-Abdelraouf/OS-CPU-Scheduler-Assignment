package com.os.backend.Process;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Process implements Cloneable{
    private int processNumber;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int turnaroundTime;
    private int waitingTime;


    public Process(int processNumber, int arrivalTime, int burstTime) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public Process() {
        this(0, 0, 1);
    }

    // Getters and Setters

    public int getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime= arrivalTime;
    }


    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }


    @Override
    public String toString() {
        return "Process{" +
                "processNumber=" + processNumber +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", remainingTime=" + remainingTime +
                ", turnaroundTime=" + turnaroundTime +
                ", waitingTime=" + waitingTime +
                '}';
    }

    @Override
    public Process clone() {
        try {
            Process clone = (Process) super.clone();
            clone.processNumber = this.processNumber;
            clone.arrivalTime = this.arrivalTime;
            clone.burstTime = this.burstTime;
            clone.remainingTime = this.remainingTime;
            clone.waitingTime = this.waitingTime;
            clone.turnaroundTime = this.turnaroundTime;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
