/**
 * Sample Skeleton for 'my-product.fxml' Controller Class
 */

package org.fiek.controllers.product;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;

import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.FlowPane;

import javafx.scene.layout.HBox;

import org.fiek.App;

import org.fiek.controllers.layout.LayoutController;

import org.fiek.models.Product;

import org.fiek.models.User;

import org.fiek.services.product.GetAllProductByUser;

import org.fiek.store.BaseStore;

import org.fiek.store.auth.AuthStore;

import org.fiek.store.product.ProductStore;


public class MyProductController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);

    ProductStore productStore = baseStore.getProductStore();

    AuthStore authStore = baseStore.getAuthStore();

    User user = authStore.getUser();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeFirstHbox"
    private HBox homeFirstHbox; // Value injected by FXMLLoader

    @FXML // fx:id="homeAllPrdouct"
    private FlowPane homeAllPrdouct; // Value injected by FXMLLoader

    @FXML // fx:id="createId"
    private JFXButton createId; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeFirstHbox != null : "fx:id=\"homeFirstHbox\" was not injected: check your FXML file 'my-product.fxml'.";
        assert homeAllPrdouct != null : "fx:id=\"homeAllPrdouct\" was not injected: check your FXML file 'my-product.fxml'.";


        GetAllProductByUser service = new GetAllProductByUser(user);

        service.start();

        service.setOnSucceeded(e -> {

            ArrayList<Product> productsUser = productStore.getProductUserList();

            for (Product product : productsUser) {

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


            createId.setOnAction(e2 -> {

                AnchorPane anchorPane1;

                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/product/add-product-1.fxml"));

                try {
                    anchorPane1 = fxmlLoader.load();
                    LayoutController.exportMain.setContent(null);
                    LayoutController.exportMain.setContent(anchorPane1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });


        });


    }

}
