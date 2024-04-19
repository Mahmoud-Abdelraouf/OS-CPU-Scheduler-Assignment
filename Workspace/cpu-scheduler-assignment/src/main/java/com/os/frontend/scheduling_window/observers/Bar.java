package com.os.frontend.scheduling_window.observers;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessAtTime;
import com.os.backend.main.SystemScheduler;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;

public class Bar extends AnchorPane implements Observer, Initializable {

    private List<ProcessAtTime> processAtTimeList;

    @FXML
    public BarChart<String, Double> bar;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    private List<Process> processList;

    public Bar(){
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();

        // Set labels for the axes
        this.xAxis.setLabel("Process");
        this.yAxis.setLabel("Waiting time");
        this.bar = new BarChart(xAxis, yAxis);
    }
    @Override
    public void update(SystemScheduler SystemScheduler) {
        bar.getData().clear();
        //TODO: update bars with the new data, ignore the system
        for (Process process : processList) {
            var series = new XYChart.Series();
            series.setName(String.valueOf(process.getProcessNumber()));
            series.getData().add(new XYChart.Data("", process.getWaitingTime()));
            bar.getData().add(series);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}