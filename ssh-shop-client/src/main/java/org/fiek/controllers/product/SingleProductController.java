package org.fiek.controllers.product;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.fiek.models.Product;

public class SingleProductController {

    final Product product;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox homeProduct1;

    @FXML
    private ImageView productImage1;

    @FXML
    private Label title;

    @FXML
    private Label category;

    @FXML
    private Label description;

    public SingleProductController(Product product) {
        this.product = product;
    }

    @FXML
    void initialize() {
        assert homeProduct1 != null : "fx:id=\"homeProduct1\" was not injected: check your FXML file 'single-item.fxml'.";
        assert productImage1 != null : "fx:id=\"productImage1\" was not injected: check your FXML file 'single-item.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'single-item.fxml'.";
        assert category != null : "fx:id=\"catgory\" was not injected: check your FXML file 'single-item.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'single-item.fxml'.";

        if(product != null){
            System.out.println(product.getName());
            title.setText(product.getName());
            category.setText(product.getCategoryId() + "");
            description.setText(product.getDescription());

        }

    }
}
