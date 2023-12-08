module com.example.gestql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.gestql to javafx.fxml;
    exports com.example.gestql;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Models;
    opens Models to javafx.fxml;


}