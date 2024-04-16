module com.os.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    opens com.os.backend.Process to javafx.base;


    opens com.os.frontend to javafx.fxml;
    exports com.os.frontend;
    exports com.os.frontend.start_window;
    opens com.os.frontend.start_window to javafx.fxml;

    exports com.os.backend.Process;
    exports com.os.backend.main;
    exports com.os.backend.Schedule;
}
