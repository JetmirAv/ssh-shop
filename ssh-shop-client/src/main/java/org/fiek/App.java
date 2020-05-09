package org.fiek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.layout.LayoutController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App  {

    private static Scene scene;
    public static BorderPane main;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("views/layout/index", new LayoutController()));
        stage.setMinWidth(960);
        stage.setMinHeight(640);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml, AbstractController controller) throws IOException {
        scene.setRoot(loadFXML(fxml, controller));
    }

    private static Parent loadFXML(String fxml, AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception {
        //launch();
        String type = "json";
        String cmd = "ser";
        String filepath = "src/main/java/org/fiek/utils/json/java2.json";
        System.out.println(type + ", " + cmd + ", " + filepath);

        User model = new User("John Doe", 24, new ArrayList<>());
        model.emails.add("john.doe.java@example.com");
        System.out.println(model);
        String rawData = "";
        rawData = new JsonHelper<User>(User.class).serializeJson(model, filepath);
        System.out.println("serialized object: " + rawData);
        User user = null;
        model = new JsonHelper<User>(User.class).deserializeJson(filepath);
        System.out.println("deserialized object: " + model.toString());
    }
}
