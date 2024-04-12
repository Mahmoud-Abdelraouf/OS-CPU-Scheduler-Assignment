package com.os.frontend;
import com.os.backend.Process.Process;
import com.os.backend.Process.PriorityProcess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class SchedulerViewController implements Initializable{


    @FXML
    private TableView<Process> processTable;
    @FXML
    private TableColumn<Process, Integer> pidColumn;
    @FXML
    private TableColumn<Process, Integer> arrivalColumn;
    @FXML
    private TableColumn<Process, Integer>  burstColumn;
    @FXML
    private TableColumn<Process, Integer> priorityColumn;
    @FXML
    private TableColumn<Process, Integer> remainingColumn;

    public BarChart barChart;
    public HBox ganttBox;
    public Button addButton;
    public TextField arrivalField;
    public TextField priorityField;
    public TextField burstField;
    public Button stopButton;
    public Button startButton;

    ObservableList<Process> list  = FXCollections.observableArrayList(
    new Process(0,5 , 6 ),
    new Process(0, 30 , 28 ),
    new Process(0, 1 , 7 )

    );

    public void addNewProcess(ActionEvent actionEvent) {
        PriorityProcess process = new PriorityProcess(0, parseInt(arrivalField.getText()),parseInt(burstField.getText()),parseInt(priorityField.getText()));




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: pidColumn.setCellFactory(new PropertyValueFactory<Process,Integer>()); Handle pid
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));
        burstColumn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("burstTime"));
        //TODO:remainingColumn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime"));

        processTable.setItems(list);
    }
}
