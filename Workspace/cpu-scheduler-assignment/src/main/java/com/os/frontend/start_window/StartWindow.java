package com.os.frontend.start_window;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StartWindow extends StackPane {
    public StartWindow() {

        getChildren().add(new Button("Close"));
    }
}
