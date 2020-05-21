package org.fiek.utils;

import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.fiek.App;
import org.fiek.controllers.modal.ModalController;
import org.fiek.models.Error;

public class ErrorHandler {

    public static Stage stage;

    public static void handle(String response) throws Exception {
        Error error = new GsonBuilder().create().fromJson(response, Error.class);
        System.out.println("Ka ardh knej: " + error.getMessage());

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/modal/modal.fxml"));
        ModalController modalController = new ModalController();
        System.out.println("modalController " + modalController);
        modalController.setMessageText(error.getMessage());
        modalController.setError(true);
        fxmlLoader.setController(modalController);
        Scene scene = new Scene(fxmlLoader.load());

//        ModalController modalController = new ModalController();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        });

    }

}
