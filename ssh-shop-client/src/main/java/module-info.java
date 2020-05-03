module org.fiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens org.fiek to javafx.fxml;
    opens org.fiek.controllers.primary to javafx.fxml;
    opens org.fiek.controllers.secondary to javafx.fxml;
    exports org.fiek;
}