package com.os.frontend.schedulling_window.observers;

import com.os.backend.Process.ProcessAtTime;
import com.os.backend.main.System;
import com.os.frontend.Colors.Colors;
import com.os.frontend.Main;
import com.os.frontend.start_window.StartWindowController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.chart.BarChart;

public class Bar extends AnchorPane implements Observer, Initializable {

    private List<ProcessAtTime> processAtTimeList;

    @FXML
    public BarChart<String, Double> Bar;

    @Override
    public void update(System system) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var series1=new XYChart.Series();
        series1.setName("P1");
        series1.getData().add(new XYChart.Data("",40));


        var series2=new XYChart.Series();
        series2.setName("P2");
        series2.getData().add(new XYChart.Data("",50));

        var series3=new XYChart.Series();
        series3.setName("P3");
        series3.getData().add(new XYChart.Data("",12));
        var series4=new XYChart.Series();
        series4.setName("P4");
        series4.getData().add(new XYChart.Data("",16));

//        new Thread(()->{
//            try {
//                Thread.sleep(5000);
//                Platform.runLater(()->series1.getNode().setStyle("-fx-bar-fill: green;"));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }).start();


        Bar.getData().addAll(series1,series2,series3,series4);

        for(int i=0;i<40;i++){
            var series=new XYChart.Series();
            series.setName("P"+(i+5));
            series.getData().add(new XYChart.Data("",16));
            Bar.getData().add(series);
        }
    }
}