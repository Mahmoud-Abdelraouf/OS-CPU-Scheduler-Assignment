module com.os.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.os.frontend to javafx.fxml;
    exports com.os.frontend;
}