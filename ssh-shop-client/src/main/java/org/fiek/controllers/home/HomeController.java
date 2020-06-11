package org.fiek.controllers.home;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.fiek.App;
import org.fiek.controllers.product.SingleProductController;
import org.fiek.models.Product;
import org.fiek.services.product.GetAllProductsService;
import org.fiek.store.BaseStore;
import org.fiek.store.product.ProductStore;

public class HomeController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    ProductStore productStore = baseStore.getProductStore();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox homeFirstHbox;

    @FXML
    private FlowPane homeAllPrdouct;

    @FXML
    void initialize() {
        assert homeFirstHbox != null : "fx:id=\"homeFirstHbox\" was not injected: check your FXML file 'home.fxml'.";
        assert homeAllPrdouct != null : "fx:id=\"homeAllPrdouct\" was not injected: check your FXML file 'home.fxml'.";

        GetAllProductsService getAddressService = new GetAllProductsService();
        getAddressService.start();

        getAddressService.setOnSucceeded(e -> {
            ArrayList<Product> products = productStore.getProductList();

            for (Product product : products) {

                FXMLLoader loader = new FXMLLoader(App.class.getResource("views/product/single-item.fxml"));
                SingleProductController controller = new SingleProductController(product);
                loader.setController(controller);

                try {
                    AnchorPane item = (AnchorPane) loader.load();
                    homeAllPrdouct.getChildren().add(item);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            }
        });

    }
}
