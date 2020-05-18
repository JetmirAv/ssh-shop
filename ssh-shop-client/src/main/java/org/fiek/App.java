package org.fiek;

import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.layout.LayoutController;
import org.fiek.store.auth.AuthStore;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static BorderPane main;
    static EasyDI context = new EasyDI();

    @Override
    public void start(Stage stage) throws IOException {

        AuthStore authStore = new AuthStore();
        context.bindInstance(AuthStore.class, authStore);

        scene = new Scene(loadFXML("views/layout/index"));
        stage.setMinWidth(960);
        stage.setMinHeight(640);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(context::getInstance);
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml, AbstractController controller) throws IOException {
        scene.setRoot(loadFXML(fxml, controller));
    }

    public static Parent loadFXML(String fxml, AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

}
