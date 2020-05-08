module org.fiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;

    opens org.fiek to javafx.fxml;
    exports org.fiek;
}