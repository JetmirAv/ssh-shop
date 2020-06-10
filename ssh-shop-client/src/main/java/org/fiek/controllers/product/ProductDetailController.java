package org.fiek.controllers.product; /**
 * Sample Skeleton for 'product-details.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.fiek.services.product.GetProductDetailService;

public class ProductDetailController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="productImageId"
    private ImageView productImageId; // Value injected by FXMLLoader

    @FXML // fx:id="titleId"
    private Label titleId; // Value injected by FXMLLoader

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


        System.out.println("hinem");

        GetProductDetailService service = new GetProductDetailService();
        service.start();

        service.setOnSucceeded(e->{
            System.out.println("Mir o!");
        });
    }
}
