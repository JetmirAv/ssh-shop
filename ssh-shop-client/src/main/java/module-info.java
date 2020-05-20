module org.fiek {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;
    requires fluxfx;
    requires reactfx;
    requires easy.di;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens org.fiek to javafx.fxml;
    opens org.fiek.controllers.profile to javafx.fxml, easy.di;
    opens org.fiek.controllers.home to javafx.fxml, easy.di;
    opens org.fiek.controllers.layout to easy.di, javafx.fxml;
    opens org.fiek.controllers.auth to javafx.fxml, easy.di;
    opens org.fiek.store.auth to easy.di;

    exports org.fiek;
}