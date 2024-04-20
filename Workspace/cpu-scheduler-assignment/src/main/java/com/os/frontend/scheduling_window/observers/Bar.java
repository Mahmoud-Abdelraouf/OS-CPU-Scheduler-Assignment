package com.os.frontend.scheduling_window.observers;

import com.os.backend.Process.Process;
import com.os.backend.Process.ProcessAtTime;
import com.os.backend.main.SystemScheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public BarChart<String, Integer> barChart;
    private List<ProcessAtTime> processAtTimeList;

    @FXML
    public BarChart<String, Integer> bar;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private List<Process> processList;

    private ObservableList<String> categories;



    @Override
    public void update(SystemScheduler systemScheduler) {
        //bar.getData().clear();

        while (barChart.getData().size() != processList.size()) {
           /* System.out.println(barChart.getData().size());
            Process process = processList.get(barChart.getData().size());
            var series = new XYChart.Series();
            series.setName(String.valueOf(process.getProcessNumber()));
            series.getData().add(new XYChart.Data("", process.getRemainingTime()));
            barChart.getData().add(series);*/

            initializeChart();
        }

        Process currentRunningProcess = systemScheduler.getCurrentRunningProcess();
        //updateChartWithNewProcess(currentRunningProcess);
        int index = processList.indexOf(currentRunningProcess);
/*
        XYChart.Series<String, Integer> seriesToUpdate = barChart.getData().get(index);
        XYChart.Data<String, Integer> dataToUpdate = seriesToUpdate.getData().get(seriesToUpdate.getData().size() -1 );
        // Update the y-value of the specific data point
        dataToUpdate.setYValue(currentRunningProcess.getRemainingTime());
*/
        System.out.println(xAxis.getCategories().size());
    }

    private void updateChartWithNewProcess(Process process) {
        // Add the category for the new process
        xAxis.getCategories().add("P" + (processList.size()));

        // Create a new series for the new process
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("P" + (processList.size())); // Set a unique name for each series
        series.getData().add(new XYChart.Data<>(series.getName(), process.getBurstTime()));
        //setBarColorForSeries(series, processList.indexOf(process));


        // Add the series to the chart
        barChart.getData().add(series);

        //TODO: testing of setting a specific color to the new process in chart
        // Set the color of the series after the node is created
        /*series.nodeProperty().addListener((observable, oldNode, newNode) -> {
            if (newNode != null) {
                newNode.setStyle("-fx-background-color: RED");
            }
        });*/
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //initializeChart();
    }

    private void initializeChart() {
        xAxis.setLabel("Processes");
        yAxis.setLabel("Burst Time");

        // Create a list to store the categories
        categories = FXCollections.observableArrayList();

        // Add category names for each process in the list
        for (Process process : processList) {
            categories.add("P" + (processList.indexOf(process) + 1));
        }

        // Set the categories on the X-axis
        xAxis.setCategories(categories);

        // Create a series for each process and add it to the chart
        for (Process process : processList) {
            int seriesIndex = processList.indexOf(process);
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName("P" + (seriesIndex + 1)); // Set a unique name for each series
            series.getData().add(new XYChart.Data<>(series.getName(), process.getBurstTime()));



            barChart.getData().add(series);
        }
    }



    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}