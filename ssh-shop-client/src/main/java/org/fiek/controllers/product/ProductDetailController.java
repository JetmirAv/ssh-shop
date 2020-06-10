package org.fiek.controllers.product; /**
 * Sample Skeleton for 'product-details.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.fiek.App;
import org.fiek.models.Address;
import org.fiek.models.User;
import org.fiek.models.Variant;
import org.fiek.services.product.GetProductDetailService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

public class ProductDetailController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    User userAuth = authStore.getUser();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="productImageId"
    private ImageView productImageId; // Value injected by FXMLLoader

    @FXML // fx:id="titleId"
    private Label titleId; // Value injected by FXMLLoader

    @FXML
    private FlowPane flowPaneContainer;


    @FXML // fx:id="userId"
    private Label userId; // Value injected by FXMLLoader

    @FXML // fx:id="variant1Id"
    private Label variant1Id; // Value injected by FXMLLoader

    @FXML // fx:id="variant2Id"
    private Label variant2Id; // Value injected by FXMLLoader

    @FXML // fx:id="variant3Id"
    private Label variant3Id; // Value injected by FXMLLoader

    @FXML // fx:id="stockId"
    private Label stockId; // Value injected by FXMLLoader

    @FXML // fx:id="priceId"
    private Label priceId; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionId"
    private Label descriptionId; // Value injected by FXMLLoader

    @FXML
    private HBox hboxId;


    JFXButton jfxButton;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert productImageId != null : "fx:id=\"productImageId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert titleId != null : "fx:id=\"titleId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert userId != null : "fx:id=\"userId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert variant1Id != null : "fx:id=\"variant1Id\" was not injected: check your FXML file 'product-details.fxml'.";
        assert variant2Id != null : "fx:id=\"variant2Id\" was not injected: check your FXML file 'product-details.fxml'.";
        assert variant3Id != null : "fx:id=\"variant3Id\" was not injected: check your FXML file 'product-details.fxml'.";
        assert stockId != null : "fx:id=\"stockId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert priceId != null : "fx:id=\"priceId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert descriptionId != null : "fx:id=\"descriptionId\" was not injected: check your FXML file 'product-details.fxml'.";



        GetProductDetailService service = new GetProductDetailService();
        service.start();

        service.setOnSucceeded(e->{

            // Combinations for finding price and quantity
            Map<String, Object> details = GetProductDetailService.attributes;
            System.out.println("Details size:" + details.size());

            // Variantet
            Variant[] variants = GetProductDetailService.variants2;
            System.out.println("Length of variant:" + variants.length);

            // Emri i produktit
            String productName = GetProductDetailService.productName.replaceAll("\"", "");;
            titleId.setText(productName);


        });
    }
}
