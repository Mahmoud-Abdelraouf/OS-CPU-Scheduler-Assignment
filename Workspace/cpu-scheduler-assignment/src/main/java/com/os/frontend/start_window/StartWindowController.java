package com.os.frontend.start_window;

import com.os.backend.Process.Process;
import com.os.backend.Schedule.*;
import com.os.backend.main.Backend;
import com.os.frontend.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartWindowController extends StackPane implements Initializable {

    public FlowPane processesList;
    private final List<ProcessBlockController> processControllers = new ArrayList<>();
    public ToggleGroup toggleGroup1;
    public ToggleButton fcfsButton;
    private boolean priorityMode;
    private Main main;

    @Override
    public Node getStyleableNode() {
        return this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addProcessToList();
        hidePriorityOnProcesses(null);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void addNewProcess(MouseEvent ignoredMouseEvent) {
        addProcessToList();
    }

    public void addProcessToList() {
        HBox box = new HBox();
        FXMLLoader fxmlLoader;
        Parent p;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/com/os/frontend/ProcessBlockView.fxml"));
            p = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProcessBlockController controller = fxmlLoader.getController();
        if (!priorityMode) {
            controller.hidePriority();
        }
        processControllers.add(controller);

        Button removeButton = createRemoveButton(box, controller);

        box.getChildren().addAll(p, removeButton);
        box.getStyleClass().add("process-container");

        processesList.getChildren().add(processesList.getChildren().size() - 1, box);
    }

    private Button createRemoveButton(HBox box, ProcessBlockController controller) {
        Button removeButton = new Button("X");
        removeButton.setPrefHeight(120);
        removeButton.getStyleClass().add("remove-button");

        removeButton.setOnAction(event -> removeProcess(box, controller));

        return removeButton;
    }

    private void removeProcess(HBox box, ProcessBlockController controller) {
        if(processControllers.size() == 1){
            processControllers.get(0).reset();
            return;
        }

        processesList.getChildren().remove(box);
        processControllers.remove(controller);
        ProcessBlockController.removeProcessBlock();
        updateProcessNumbers();
    }

    private void updateProcessNumbers() {
        for (int i = 0; i < processControllers.size(); i++) {
            processControllers.get(i).setNumber(i + 1);
        }
    }

    public void resetProcessList(MouseEvent ignoredMouseEvent) {
        //remove all processes only
        processesList.getChildren().remove(0, processesList.getChildren().size() - 1);
        int size = processControllers.size();
        processControllers.subList(0, processControllers.size()).clear();

        //reset counter
        ProcessBlockController.removeProcessBlock(size);

        //add a new process
        addProcessToList();

    }

    public void hidePriorityOnProcesses(ActionEvent ignoredActionEvent) {
        if (!priorityMode) {
            return;
        }
        this.priorityMode = false;
        processControllers.forEach(ProcessBlockController::hidePriority);
    }

    public void showPriorityOnProcesses(ActionEvent ignoredActionEvent) {
        if (priorityMode) {
            return;
        }
        this.priorityMode = true;
        processControllers.forEach(ProcessBlockController::showPriority);
    }

    public void createProcesses(ActionEvent ignoredActionEvent) {
        List<Process> createdProcesses = processControllers.stream()
                .map(ProcessBlockController::createProcess)
                .toList();

        //choose the desired algorithm
        String algorithm = ((ToggleButton) toggleGroup1.getSelectedToggle()).getText();
        SchedulingAlgo schedulingAlgo = chooseAlgorithm(algorithm);

        //create the backend object
        Backend backend = new Backend();
        backend.setAlgo(schedulingAlgo);
        backend.updateProcessesList(createdProcesses);

        System.out.println("Confirm button pressed");
        System.out.println(schedulingAlgo);
        createdProcesses.forEach(System.out::println);

        //send backend to the main class, and move to the new window.
        main.setBackend(backend);
        //move to the new window
        try {
            main.moveToSchedulerView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SchedulingAlgo chooseAlgorithm(String algorithm) {
        return switch (algorithm) {
            case "FCFS" -> new FCFS();
            case "Round Robin" -> new RoundRobin();
            case "Preemptive Priority" -> new Priority_Pree();
            case "Non Preemptive Priority" -> new Priority_Non();
            case "Preemptive SJF" -> new SJF_Pree();
            case "Non Preemptive SJF" -> new SJF_Non();
            default -> throw new IllegalArgumentException("Invalid algorithm: " + algorithm);
        };
    }
}
