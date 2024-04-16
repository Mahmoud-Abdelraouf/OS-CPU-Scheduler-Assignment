package com.os.frontend;

import com.os.backend.main.Backend;
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
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        this.stage = stage;
        ((StartWindowController)fxmlLoader.getController()).setMain(this);
    }

    public void setBackend(Backend backend) {
        this.backend = backend;
    }

    public void moveToSchedulerView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SchedulerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setScene(scene);

        //TODO: start scheduling
    }
    public static void main(String[] args) {
        launch();
    }

    public Backend backend() {
        return backend;
    }
}