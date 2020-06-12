package org.fiek.controllers.product; /**
 * Sample Skeleton for 'product-details.fxml' Controller Class
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.fiek.App;
import org.fiek.models.Product;
import org.fiek.models.Cart;
import org.fiek.models.User;
import org.fiek.models.Variant;
import org.fiek.services.product.AddCartService;
import org.fiek.services.product.GetProductDetailService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.store.product.ProductStore;
import org.fiek.utils.Loading;

public class ProductDetailController {

    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    ProductStore productStore = baseStore.getProductStore();
    User userAuth = authStore.getUser();


    @FXML
    private ImageView productImageId;

    @FXML
    private VBox dataVbox;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox metaData;

    @FXML
    private JFXButton messageBtn;

    @FXML
    private Label descriptionId;

    ArrayList<String> namesList = new ArrayList<>();
    ArrayList<Variant> variants;
    ArrayList<FlowPane> flowPanesList = new ArrayList<>();
    ArrayList<ArrayList<Button>> buttonsList = new ArrayList<>();
    ArrayList<ArrayList<Button>> buttonsEventList = new ArrayList<>();
    public String combinationID;
    Label stock;
    Product product;
    Label quantity;
    Label price;
    JFXButton btn;
    String id;
    Loading loading = new Loading();

    public ProductDetailController(String id) {
        this.id = id;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert productImageId != null : "fx:id=\"productImageId\" was not injected: check your FXML file 'product-details.fxml'.";
        assert descriptionId != null : "fx:id=\"descriptionId\" was not injected: check your FXML file 'product-details.fxml'.";




        System.out.println("idja" + id);
        GetProductDetailService service = new GetProductDetailService(id);
        service.start();

        service.setOnSucceeded(e->{
            root.getChildren().remove(loading);
            product = productStore.getSelectedProduct();
            System.out.println("Produkt name : " + product.getName());
            System.out.println("Komplet : " + product);
            variants = product.getVariant();

            getListsForData();
            onActionEvents();
            System.out.println(combinationID);
//            addToCartAction();

        });
        service.setOnRunning(e2->{
            root.getChildren().add(loading);
        });

    }

    @FXML
    void createChannelHandler(ActionEvent event){

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
        quantity= new Label("Qty:1");
        btn = new JFXButton("Add to Cart");
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
                    System.out.println("lista:" + finalI + "element" + finalJ);
                    buttonsEventList.get(finalI).add(buttonsList.get(finalI).get(finalJ));
                    System.out.println(buttonsEventList);
                    setDataToLabels();
                });
            }
        }
        addToCartAction();
    }
    void setDataToLabels() {
        int k=0;
        namesList.clear();
        for (int u=0;u<buttonsEventList.size(); u++){
            if(buttonsEventList.get(u).size() == 1){
                namesList.add(buttonsEventList.get(u).get(0).getText());
                k=k+1;
            }
        }

        if (k == variants.size()) {
            for (int n=0; n<product.getCombination().size(); n++) {
                int counter =0;

                for (int j=0 ; j<namesList.size();j++){

                        if (product.getCombination().get(n).containsValue(namesList.get(j))) {
                            counter = counter + 1;
                        }
                }

                if (counter == namesList.size()) {
                    System.out.println("u ekzekutu");
                    combinationID = product.getCombination().get(n).get("_id");
                    System.out.println("combinationID:" + combinationID);
                    price.setText("Price:" + product.getCombination().get(n).get("price"));
                    stock.setText("Stock:" + product.getCombination().get(n).get("stock"));
                    counter = 0;
                }
            }
        }
    }
    void addToCartAction() {

        if (userAuth == null) {

            btn.setDisable(true);

        } else {

            btn.setDisable(false);
            btn.setOnAction(e -> {
                Cart cart = new Cart();
                cart.setUserId(userAuth.getId());
                cart.setVariantId(combinationID);
                cart.setQuantity(1);
                cart.setProductId(product.getId());

//                String json = new Gson().toJson(cart);
//                System.out.println(json);
                AddCartService addCartService = new AddCartService(userAuth.getId(), cart);
                try {
                    addCartService.addCart();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });

        }
    }
    }

