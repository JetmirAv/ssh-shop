module org.fiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;

    opens org.fiek to javafx.fxml;
    opens org.fiek.controllers.profile to javafx.fxml;
    opens org.fiek.controllers.home to javafx.fxml;
    opens org.fiek.controllers.layout to javafx.fxml;
    exports org.fiek;
}