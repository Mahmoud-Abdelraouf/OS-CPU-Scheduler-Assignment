package com.os.frontend.schedulling_window.observers;

import com.os.backend.main.SystemScheduler;

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
