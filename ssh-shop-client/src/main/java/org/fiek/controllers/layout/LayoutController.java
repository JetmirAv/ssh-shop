package org.fiek.controllers.layout;

/**
 * Sample Skeleton for 'index.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.fiek.App;
import org.fiek.controllers.AbstractController;
import org.fiek.controllers.profile.ProfileController;
import org.fiek.store.auth.AuthStore;

public class LayoutController extends AbstractController implements View {

    AuthStore authStore = AuthStore.getInstance();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainPane"
    private BorderPane mainPane; // Value injected by FXMLLoader

    @FXML // fx:id="header"
    private HBox header; // Value injected by FXMLLoader

    @FXML // fx:id="navigator"
    private GridPane navigator; // Value injected by FXMLLoader

    @FXML // fx:id="navHome"
    private JFXButton navHome; // Value injected by FXMLLoader

    @FXML // fx:id="navCart"
    private JFXButton navCart; // Value injected by FXMLLoader

    @FXML // fx:id="navMyProducts"
    private JFXButton navMyProducts; // Value injected by FXMLLoader

    @FXML // fx:id="navWishlist"
    private JFXButton navWishlist; // Value injected by FXMLLoader

    @FXML // fx:id="navChat"
    private JFXButton navChat; // Value injected by FXMLLoader

    @FXML // fx:id="content"
    private AnchorPane content; // Value injected by FXMLLoader

    @FXML // fx:id="main"
    private ScrollPane main; // Value injected by FXMLLoader

    @FXML
    void navClick(ActionEvent event) {

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'index.fxml'.";
        assert header != null : "fx:id=\"header\" was not injected: check your FXML file 'index.fxml'.";
        assert navigator != null : "fx:id=\"navigator\" was not injected: check your FXML file 'index.fxml'.";
        assert navHome != null : "fx:id=\"navHome\" was not injected: check your FXML file 'index.fxml'.";
        assert navCart != null : "fx:id=\"navCart\" was not injected: check your FXML file 'index.fxml'.";
        assert navMyProducts != null : "fx:id=\"navMyProducts\" was not injected: check your FXML file 'index.fxml'.";
        assert navWishlist != null : "fx:id=\"navWishlist\" was not injected: check your FXML file 'index.fxml'.";
        assert navChat != null : "fx:id=\"navChat\" was not injected: check your FXML file 'index.fxml'.";
        assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'index.fxml'.";
        assert main != null : "fx:id=\"main\" was not injected: check your FXML file 'index.fxml'.";


        main.setContent(App.loadFXML("views/home/home"));

        System.out.println(authStore.getToken());

        if (authStore.getUser() == null) {
            header.getChildren().add(App.loadFXML("views/layout/no-auth"));
        } else {
            header.getChildren().add(App.loadFXML("views/layout/profile", new ProfileController()));
        }


        authStore.getTokenSource().subscribe(this::authorize);

    }

    private void authorize(String s) {
        try {
            System.out.println("Pub:" + s);
            header.getChildren().clear();
            if (s == null) {
                header.getChildren().add(App.loadFXML("views/layout/no-auth"));
            } else {
                header.getChildren().add(App.loadFXML("views/layout/profile"));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @FXML
    void navClick(Event event) throws IOException {
        String id = ((Node) event.getSource()).getId();
        System.out.println(id);

        removeActiveClass();

        switch (id) {
            case "navHome":
                setRoot("views/home/home");
                navHome.getStyleClass().add("active");
                break;

            case "navCart":
                setRoot("views/cart/cart");
                navCart.getStyleClass().add("active");
                break;

            case "navMyProducts":
                setRoot("views/home/home");
                navMyProducts.getStyleClass().add("active");
                break;

            case "navWishlist":
                setRoot("views/home/home");
                navWishlist.getStyleClass().add("active");
                break;

            case "navChat":
                setRoot("views/chat/chat");
                navChat.getStyleClass().add("active");
                break;

            case "profile":
                setRoot("views/profile/index", new ProfileController());
                break;

        }

    }

    void removeActiveClass() {
        navHome.getStyleClass().removeAll("active");
        navCart.getStyleClass().removeAll("active");
        navMyProducts.getStyleClass().removeAll("active");
        navWishlist.getStyleClass().removeAll("active");
        navChat.getStyleClass().removeAll("active");
    }

    public void setRoot(String fxml) throws IOException {
        main.setContent(null);
        main.setContent(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void setRoot(String fxml, AbstractController controller) throws IOException {
        main.setContent(loadFXML(fxml, controller));
    }

    private static Parent loadFXML(String fxml, AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

}




