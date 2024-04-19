package com.os.frontend;

import com.os.backend.main.Backend;
import com.os.frontend.scheduling_window.components.SchedulerWindow;
import com.os.frontend.start_window.StartWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Backend backend;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StartWindowView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Scheduler");
        stage.setScene(scene);
        stage.show();

        this.stage = stage;
        ((StartWindowController)    fxmlLoader.getController()).setMain(this);
    }

    public void setBackend(Backend backend) {
        this.backend = backend;
    }

    public void moveToSchedulerView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/os/frontend/schedulerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SchedulerWindow schedulerWindowController = fxmlLoader.getController();
        //set the backend -->  attach observers automatically
        schedulerWindowController.setBackend(backend);
        //set the stage to the new scene
        this.stage.setScene(scene);
        //start scheduling
        this.backend.startSchedule();

        /*//----------------------------------------------------------------
        // For Testing
        // TODO: add this code to the scheduler view
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProcessesTableView.fxml"));
        // set the new scene
        Scene scene = new Scene(fxmlLoader.load());
        // attach and init the controller
        ProcessesTable processesTableController = fxmlLoader.getController();
        this.backend.attach(processesTableController);
        // make the backend notify the observers
        this.backend.startSchedule();

        this.stage.setScene(scene);
        //----------------------------------------------------------------*/

        //TODO: start scheduling
    }
    public static void main(String[] args) {
        launch();
    }

    public Backend backend() {
        return backend;
    }
}