package org.fiek.controllers.profile;

/**
 * Sample Skeleton for 'index.fxml' Controller Class
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.fiek.App;
import org.fiek.controllers.AbstractController;

public class ProfileController extends AbstractController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="profileBox"
    private Pane profileBox; // Value injected by FXMLLoader

    @FXML // fx:id="navInfo"
    private Button navInfo; // Value injected by FXMLLoader

    @FXML // fx:id="navPassword"
    private Button navPassword; // Value injected by FXMLLoader

    @FXML // fx:id="navAddresses"
    private Button navAddresses; // Value injected by FXMLLoader

    @FXML // fx:id="navCards"
    private Button navCards; // Value injected by FXMLLoader



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert profileBox != null : "fx:id=\"profileBox\" was not injected: check your FXML file 'index.fxml'.";
        setRoot("views/profile/info");

    }
    @FXML
    void navClick(Event event)throws IOException {
        String id = ((Node) event.getSource()).getId();
        removeActiveClass();

        switch (id) {
            case "navInfo":
                setRoot("views/profile/info");
                navInfo.getStyleClass().add("active");
                break;

            case "navPassword":
                setRoot("views/profile/password");
                navPassword.getStyleClass().add("active");
                break;

            case "navAddresses":
                setRoot("views/profile/address");
                navAddresses.getStyleClass().add("active");
                break;

            case "navCards":
                setRoot("views/profile/cards");
                navCards.getStyleClass().add("active");
                break;



        }

    }
    public void setRoot(String fxml) throws IOException {
        profileBox.getChildren().clear();
        profileBox.getChildren().add(loadFXML(fxml));

    }



    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void setRoot(String fxml, AbstractController controller) throws IOException {
        profileBox.getChildren().removeAll();
        profileBox.getChildren().add(loadFXML(fxml, controller));
    }

    private static Parent loadFXML(String fxml, AbstractController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }
    void removeActiveClass(){
        navInfo.getStyleClass().remove("active");
        navPassword.getStyleClass().remove("active");
        navAddresses.getStyleClass().remove("active");
        navCards.getStyleClass().remove("active");
    };
}

