package com.os.frontend.scheduling_window.components;

import com.os.backend.Process.PriorityProcess;
import com.os.backend.Process.Process;
import com.os.backend.main.Backend;
import com.os.frontend.Main;
import com.os.frontend.scheduling_window.observers.Bar;
import com.os.frontend.scheduling_window.observers.Observer;
import com.os.frontend.start_window.ProcessBlockController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SchedulerWindow extends StackPane implements Initializable {
    public HBox ganttChartBox;
    public HBox barAndTableBox;
    public HBox addProcessBox;
    public VBox timerBox;
    public Label timeLabel;
    private Backend backend;
    private ProcessBlockController processBlockController;
    // used for attaching observers to the backend
    private final List<Observer> observers = new ArrayList<>(3);
    private int seconds;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ganttCharInit();
            tableInit();
            barChartInit();
            addProcessInit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void ganttCharInit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/os/frontend/GanttChartView.fxml"));
        ganttChartBox.getChildren().add(fxmlLoader.load());
        // add to observers
        this.observers.add(fxmlLoader.getController());
    }

    private void barChartInit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/os/frontend/Bar.fxml"));
        barAndTableBox.getChildren().add(fxmlLoader.load());
        // add to observers
        this.observers.add(fxmlLoader.getController());
    }

    private void tableInit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/os/frontend/ProcessesTableView.fxml"));
        barAndTableBox.getChildren().add(fxmlLoader.load());
        // add to observers
        this.observers.add(fxmlLoader.getController());
    }

    private void addProcessInit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/os/frontend/ProcessBlockView.fxml"));
        this.addProcessBox.getChildren().add(2, fxmlLoader.load());
        this.processBlockController = fxmlLoader.getController();
    }

    private void timerInit() {
        // Create a timeline that triggers every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        event -> {
                            // Update the label with the current time
                            timeLabel.setText(String.valueOf(seconds));
                            // Update the arrival time of the processBlockController to the next second
                            this.processBlockController.setArrivalTime(seconds + 1);
                            //update seconds
                            seconds++;
                        }
                ),
                new KeyFrame(Duration.seconds(1)) // Trigger every second
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        // Start the timeline
        timeline.play();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public void setBackend(Backend backend) {
        this.backend = backend;
        // attach observers to the backend
        this.observers.forEach(backend::attach);
        //set the processBlock into the priority mode.
        if (this.backend.getProcessList().get(0) instanceof PriorityProcess) {
            // priority mode
            this.processBlockController.showPriority();
        } else {
            this.processBlockController.hidePriority();
        }

        //set process list to the bar chart
        ((Bar) this.observers.get(2)).setProcessList(this.backend.getProcessList());

        //timer init
        timerInit();
    }

    public void addNewProcessToBackEnd(ActionEvent ignoredActionEvent) {
        Process newProcess = this.processBlockController.createProcess();
        // add the process to the backend
        this.backend.addProcess(newProcess);
        // increment the number of the addNewProcess block
        this.processBlockController.incrementProcessNumber();
    }

    public void resetProcessBlock(ActionEvent ignoredActionEvent) {
        //reset the processBlock data except the number
        this.processBlockController.reset();
    }
}
