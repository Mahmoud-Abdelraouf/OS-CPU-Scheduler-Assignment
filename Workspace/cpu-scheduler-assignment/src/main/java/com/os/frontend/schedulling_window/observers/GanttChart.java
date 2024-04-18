package com.os.frontend.schedulling_window.observers;

import com.os.backend.Process.Process;
import com.os.backend.main.System;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GanttChart extends StackPane implements Observer, Initializable {

    private int time;
    @Override
    public void update(System system) {
        Process currentProcess = system.getCurrentProcess();
        int index = currentProcess.getProcessNumber() - 1;
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
