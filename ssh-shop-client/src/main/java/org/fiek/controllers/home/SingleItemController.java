package org.fiek.controllers.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.fiek.models.Product;
import org.fiek.models.User;
import org.fiek.store.BaseStore;
import org.fiek.store.home.HomeStore;
import org.fiek.utils.ImageUploadHandler;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingleItemController {

    private final BaseStore baseStore;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeProductTitle1Id"
    private Label homeProductTitle1Id; // Value injected by FXMLLoader

    @FXML // fx:id="singleItemRoot"
    private AnchorPane singleItemRoot; // Value injected by FXMLLoader

    @FXML // fx:id="homeProductDescription1Id"
    private Label homeProductDescription1Id; // Value injected by FXMLLoader

    @FXML // fx:id="productImage1Id"
    private ImageView productImage1Id; // Value injected by FXMLLoader

    @FXML // fx:id="itemRoot"
    private AnchorPane itemRoot; // Value injected by FXMLLoader

    @FXML // fx:id="homeProduct1"
    private VBox homeProduct1; // Value injected by FXMLLoader

    @FXML // fx:id="homeProductCategory1Id"
    private Label homeProductCategory1Id; // Value injected by FXMLLoader

    public SingleItemController(BaseStore baseStore) {
        this.baseStore = baseStore;
    }

    HomeStore homeStore;
    User user;
    Product product;
    String category;
    Loading loading;
    Tuple<Image, String> customImage;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeProductTitle1Id != null : "fx:id=\"homeProductTitle1Id\" was not injected: check your FXML file 'single-item.fxml'.";
        assert singleItemRoot != null : "fx:id=\"singleItemRoot\" was not injected: check your FXML file 'single-item.fxml'.";
        assert homeProductDescription1Id != null : "fx:id=\"homeProductDescription1Id\" was not injected: check your FXML file 'single-item.fxml'.";
        assert productImage1Id != null : "fx:id=\"productImage1Id\" was not injected: check your FXML file 'single-item.fxml'.";
        assert itemRoot != null : "fx:id=\"itemRoot\" was not injected: check your FXML file 'single-item.fxml'.";
        assert homeProduct1 != null : "fx:id=\"homeProduct1\" was not injected: check your FXML file 'single-item.fxml'.";
        assert homeProductCategory1Id != null : "fx:id=\"homeProductCategory1Id\" was not injected: check your FXML file 'single-item.fxml'.";


        homeStore = baseStore.getHomeStore();

    }

    @FXML
    private void productInfoHandler(ActionEvent event) {

        product.setName(homeProductTitle1Id.getText());
        product.setDescription(homeProductDescription1Id.getText());
        //product.setCategory(homeProductCategory1Id.getText());



    }

    @FXML
    void productImage(ActionEvent event) throws FileNotFoundException {
        ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
        customImage = imageUploadHandler.uploadImage();
        productImage1Id.setImage(customImage.getFirst());
        productImage1Id.setPreserveRatio(false);
        user.setAvatar(customImage.getSecond());

    }


}
