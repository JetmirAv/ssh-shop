package org.fiek.controllers.product; /**
 * Sample Skeleton for 'product-details.fxml' Controller Class
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.fiek.App;
import org.fiek.models.Product;
import org.fiek.models.User;
import org.fiek.models.Variant;
import org.fiek.services.product.GetProductDetailService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

public class ProductDetailController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    User userAuth = authStore.getUser();


    @FXML
    private ImageView productImageId;

    @FXML
    private VBox dataVbox;

    @FXML
    private VBox metaData;

    @FXML
    private Label descriptionId;
    ArrayList<String> namesList = new ArrayList<>();
    ArrayList<Variant> variants;
    Product product;
    ArrayList<FlowPane> flowPanesList = new ArrayList<>();
    ArrayList<ArrayList<Button>> buttonsList = new ArrayList<>();
    ArrayList<ArrayList<Button>> buttonsEventList = new ArrayList<>();
    public String combinationID;
    Label stock;
    Label quantity;
    Label price;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert productImageId != null : "fx:id=\"productImageId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert descriptionId != null : "fx:id=\"descriptionId\" was not injected: check your FXML file 'product-details.fxml'.";



        GetProductDetailService service = new GetProductDetailService();
        service.start();

        service.setOnSucceeded(e->{
            product = GetProductDetailService.productStatic;
            variants = product.getVariant();

            getListsForData();
            onActionEvents();
        });

    }
    void getListsForData(){

        HBox nameHBox = new HBox();
        Label namelabel = new Label(product.getName());
        nameHBox.getChildren().add(namelabel);
        metaData.getChildren().add(nameHBox);
        for (int i=0; i<variants.size();i++) {
            FlowPane variantsFlowPane = new FlowPane();
            VBox vBox = new VBox();
            flowPanesList.add(variantsFlowPane);
            variantsFlowPane.setVgap(10);
            variantsFlowPane.setHgap(10);
            buttonsEventList.add(new ArrayList<>());

            buttonsList.add(new ArrayList<Button>());
            for (int k = 0; k < variants.get(i).options.size(); k++) {
                JFXButton button = new JFXButton(variants.get(i).getOptions().get(k));
                button.getStyleClass().add("detailViewButtons");
                flowPanesList.get(i).getChildren().add(button);
                buttonsList.get(i).add(button);
            }
            HBox namesHBox = new HBox();
            namesHBox.getChildren().add(new Label(variants.get(i).getName()));
            vBox.getChildren().addAll(namesHBox, flowPanesList.get(i));
            dataVbox.getChildren().add(vBox);
        }
        HBox hBox = new HBox();
        stock= new Label("Stock:");
        hBox.getChildren().add(stock);
        hBox.setAlignment(Pos.CENTER_LEFT);

        HBox hBox1 = new HBox();
        price= new Label("Price:");
        hBox1.getChildren().add(price);
        hBox1.setAlignment(Pos.CENTER_LEFT);

        HBox hBox2 = new HBox();
        quantity= new Label("Qty:");
        JFXButton btn = new JFXButton("Add to Cart");
        btn.getStyleClass().add("product-button");
        hBox2.getChildren().addAll(quantity,btn);
        hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setSpacing(50);

        dataVbox.getChildren().addAll(hBox,hBox1,hBox2);

        descriptionId.setText(product.getDescription());

    }

    void onActionEvents(){
        var ref = new Object() {
            AtomicInteger finalN = new AtomicInteger();
        };
        //int n;
        for (int i=0; i<buttonsList.size(); i++){
            for(int j=0 ; j<buttonsList.get(i).size(); j++){
                int finalI = i;
                int finalJ = j;
                buttonsList.get(i).get(j).setOnAction(e->{
                    int m =0;
                    buttonsEventList.get(finalI).clear();
                    buttonsEventList.get(finalI).add(buttonsList.get(finalI).get(finalJ));
                    setDataToLabels();
                });
            }
        }
    }
    void setDataToLabels() {
        int k=0;
        for (int u=0;u<buttonsEventList.size(); u++){
            if(buttonsEventList.get(u).size() == 1){
                namesList.add(buttonsEventList.get(u).get(0).getText());
                k=k+1;
            }
        }

        if (k == variants.size()) {
            for (Map<String, String> combinations : product.getCombination()) {
                int counter = 0;
                for (int i = 0; i < product.getCombination().size(); i++) {
                    if (combinations.containsValue(namesList.get(i))) {
                        counter = counter + 1;
                    }
                    if (counter == product.getCombination().size()) {
                        combinationID = combinations.get("_id");
                        price.setText("Price:" + combinations.get("price"));
                        stock.setText("Stock:" + combinations.get("stock"));
                    }
                }
            }
        }

    }

}
