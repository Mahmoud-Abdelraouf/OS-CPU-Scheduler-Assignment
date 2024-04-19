package com.os.frontend.scheduling_window.observers;

import com.os.backend.main.SystemScheduler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GanttChart extends StackPane implements Observer, Initializable {

    private int time;
    @Override
    public void update(SystemScheduler systemScheduler) {
        //TODO

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
