module org.fiek {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.fiek to javafx.fxml;
    exports org.fiek;
}