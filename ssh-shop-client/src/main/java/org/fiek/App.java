package org.fiek;

import eu.lestard.easydi.EasyDI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.layout.LayoutController;
import org.fiek.socket.SocketClient;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

import java.io.IOException;
import java.net.Socket;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static BorderPane main;
    public static EasyDI context = new EasyDI();

    public static Stage primmaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        SocketClient socketClient = new SocketClient("http://0.0.0.0:3001/");
        context.bindInstance(SocketClient.class, socketClient);

        BaseStore baseStore = new BaseStore();
        context.bindInstance(BaseStore.class, baseStore);

        scene = new Scene(loadFXML("views/layout/index"));

        primmaryStage = stage;

        stage.setOnCloseRequest(e -> {
            socketClient.getSocket().disconnect();
            Platform.exit();
        });
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
        fxmlLoader.setControllerFactory(context::getInstance);
        return fxmlLoader.load();
    }

}
