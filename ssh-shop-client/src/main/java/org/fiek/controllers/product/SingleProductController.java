package org.fiek.controllers.product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.fiek.App;
import org.fiek.controllers.layout.LayoutController;
import org.fiek.models.Product;
import org.fiek.services.product.GetProductDetailService;

public class SingleProductController implements View {

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

    public static String product_id;

    public SingleProductController(Product product) {
        this.product = product;
    }
    public FXMLLoader fxmlLoader;
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

            homeProduct1.setOnMouseClicked(a->{
                System.out.println(product.getId());
                product_id = product.getId();
                AnchorPane anchorPane1;
                System.out.println("Product" + product.getName());
                fxmlLoader = new FXMLLoader(App.class.getResource("views/product/product-details.fxml"));
                fxmlLoader.setController(new ProductDetailController(product.getId()));

                try {
                    anchorPane1 = fxmlLoader.load();
                    LayoutController.exportMain.setContent(null);
                    LayoutController.exportMain.setContent(anchorPane1);
//                    fxmlLoader = new FXMLLoader(App.class.getResource("views/profile/list-Item-Cards.fxml"));
//                    fxmlLoader.setController(new ListItemCardController(card));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                product_id = product.getId();



            });

        }

    }
}
