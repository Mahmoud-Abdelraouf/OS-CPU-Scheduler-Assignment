package com.os.frontend.scheduling_window.observers;

import com.os.backend.Process.Process;
import com.os.backend.main.SystemScheduler;
import com.os.frontend.Colors.Colors;
import com.os.frontend.scheduling_window.observers.Observer;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GanttChart extends ScrollPane implements Observer, Initializable {

    private int time;
    @Override
    public void update(SystemScheduler system) {
        Process currentProcess = system.getCurrentRunningProcess();
        int index = currentProcess.getProcessNumber();

        StackPane box;

        if (currentProcess == null) {
            box = addIdlebox();
        } else {
           box =  addProcessBox(index);
        }

        this.getChildren().add(box);
        this.setPrefWidth(this.getPrefWidth()+9);
        scrolViewChange();

    }


    private void scrolViewChange(){
        time++;
        if (time>19)
            this.setHvalue(1);

    }
    public  StackPane addProcessBox(int index ){
        StackPane box = new StackPane();
        box.setPrefHeight(75);
        box.setPrefWidth(50);
        String colorStyle = Colors.getColor(index);
        box.setStyle("-fx-background-color:"+  colorStyle);
        Label label = new Label("P" + (index));
        label.setStyle("-fx-font-size: 12; -fx-font-weight: bold");
        box.getChildren().add(label);
        return box;

    }

    public  StackPane addIdlebox( ){
        StackPane box = new StackPane();
        box.setPrefHeight(75);
        box.setPrefWidth(50);
        box.setStyle("-fx-background-color:  #bcbfba");
        Label label = new Label("Idle");
        label.setStyle("-fx-font-size: 10; -fx-font-weight: bold");
        box.getChildren().add(label);
        return box;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StackPane sp1 = addProcessBox(1);
        StackPane sp2 = addProcessBox(2);
        StackPane sp3 = addProcessBox(3);
        StackPane sp4 = addProcessBox(4);

        this.getChildren().addAll(sp1, sp2,sp3 ,sp4);


    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
