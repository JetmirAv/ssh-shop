package org.fiek.controllers.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import org.fiek.App;
import org.fiek.models.Combinations;
import org.fiek.models.Product;
import org.fiek.models.User;
import org.fiek.models.Variant;
import org.fiek.services.product.ProductService;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;
import org.fiek.utils.ImageUploadHandler;
import org.fiek.utils.Loading;
import org.fiek.utils.Tuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample Skeleton for 'add-product-2.fxml' Controller Class
 */

public class AddProductController1 {

    @FXML // fx:id="secondPageAP"
    private AnchorPane secondPageAP; // Value injected by FXMLLoader

    @FXML // fx:id="secondPageVBox"
    private VBox secondPageVBox; // Value injected by FXMLLoader

    @FXML // fx:id="optionsHBox"
    private FlowPane optionsHBox; // Value injected by FXMLLoader

    @FXML // fx:id="optionElements"
    private Label optionElements; // Value injected by FXMLLoader

    @FXML // fx:id="addPhotoButton"
    private Button addPhotoButton; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="priceVBox"
    private VBox priceVBox; // Value injected by FXMLLoader

    @FXML // fx:id="finishButton"
    private Button finexportAnchorPaneishButton; // Value injected by FXMLLoader

    @FXML // fx:id="quantityVBox"
    private VBox quantityVBox; // Value injected by FXMLLoader

    @FXML // fx:id="imageViewVbox"
    private VBox imageViewVbox; // Value injected by FXMLLoader

    @FXML // fx:id="addPhotoButtonHBox"
    private HBox addPhotoButtonHBox; // Value injected by FXMLLoader

    @FXML // fx:id="finishButton"
    private Button finishButton; // Value injected by FXMLLoader

    // public Button combinationButtons = new Button("Option");
    private ArrayList<Button> buttonsList = new ArrayList<Button>();
    private ArrayList<ArrayList<String>> listOfCombinations;
    private ArrayList<JFXTextField> priceTextFields = new ArrayList<JFXTextField>();
    private ArrayList<JFXTextField> quantityTextFields = new ArrayList<JFXTextField>();
    private ArrayList<ImageView> imageViewsList = new ArrayList<ImageView>();
    private ArrayList<Button> addPhotoButtonList = new ArrayList<Button>();
    private ArrayList<ArrayList<String>> combinationsList = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> variantsList = new ArrayList<ArrayList<String>>();
    private ArrayList<String> photoPathList = new ArrayList<String>();
    BaseStore baseStore = App.context.getInstance(BaseStore.class);
    AuthStore authStore = baseStore.getAuthStore();
    User user = authStore.getUser();

    private ArrayList<String> variantNames;
    private ArrayList<ArrayList<String>> oprionsList;
    private String productName;
    private String productDesc;
    Product product = new Product();
    Loading loading = new Loading();


    @FXML
    // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert secondPageAP != null : "fx:id=\"secondPageAP\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert secondPageVBox != null : "fx:id=\"secondPageVBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert optionsHBox != null : "fx:id=\"optionsHBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert optionElements != null : "fx:id=\"optionElements\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert imageViewVbox != null : "fx:id=\"imageViewVbox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert addPhotoButtonHBox != null : "fx:id=\"addPhotoButtonHBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert addPhotoButton != null : "fx:id=\"addPhotoButton\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert priceVBox != null : "fx:id=\"priceVBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert quantityVBox != null : "fx:id=\"quantityVBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'add-product-2.fxml'.";
        assert finishButton != null : "fx:id=\"finishButton\" was not injected: check your FXML file 'add-product-2.fxml'.";

        listOfCombinations = AddProductController.listToExport;
        variantNames = AddProductController.variantNamesToExport;
        productName = AddProductController.productName;
        productDesc = AddProductController.productDesc;
        oprionsList = AddProductController.variantOptions;
        getOptions(listOfCombinations);
        buttonEvents();
        buttonsList.get(0).fire();

    }

    public void getOptions(ArrayList<ArrayList<String>> listOfCombinations) throws IOException {
        for (int i = 0; i < listOfCombinations.size(); i++) {
            Button combinationButtons = new Button("Option " + (i + 1));
            optionsHBox.getChildren().add(combinationButtons);
            buttonsList.add(combinationButtons);
            combinationButtons.getStyleClass().add("combinationOptions");

            JFXTextField textField = new JFXTextField();
            textField.getStyleClass().add("add-product-fields-2");
            priceTextFields.add(textField);

            JFXTextField textField1 = new JFXTextField();
            textField1.getStyleClass().add("add-product-fields-2");
            quantityTextFields.add(textField1);

            ImageView imageView = new ImageView();
            imageView.setImage(new Image("org/fiek/images/ImagePlaceHolder.png"));
            imageViewsList.add(imageView);
            imageView.setFitHeight(330);
            imageView.setFitWidth(380);

            Button button = new Button("Add main photo");
            addPhotoButtonList.add(button);
            button.getStyleClass().add("add-product-photo-button");

        }
    }

    void buttonEvents() {
        for (int i = 0; i < buttonsList.size(); i++) {
            int finalI = i;
            buttonsList.get(i).setOnAction(e -> {

                StringBuilder options = new StringBuilder();

                priceVBox.getChildren().clear();
                priceVBox.getChildren().add(priceTextFields.get(finalI));

                quantityVBox.getChildren().clear();
                quantityVBox.getChildren().add(quantityTextFields.get(finalI));

                imageViewVbox.getChildren().clear();
                imageViewVbox.getChildren().add(imageViewsList.get(finalI));

                addPhotoButtonHBox.getChildren().clear();
                addPhotoButtonHBox.getChildren().add(addPhotoButtonList.get(finalI));

                for (int j = 0; j < listOfCombinations.get(finalI).size(); j++) {
                    options.append(" - " + listOfCombinations.get(finalI).get(j));
                }

                addPhotoButtonList.get(finalI).setOnAction(n -> {
                    ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
                    try {
                        Tuple<Image, String> costumImage;
                        costumImage = imageUploadHandler.uploadImage();
                        imageViewsList.get(finalI).setImage(costumImage.getFirst());
                        imageViewsList.get(finalI).setPreserveRatio(false);
                        photoPathList.add(costumImage.getSecond());
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                });
                optionElements.setText(options.toString().replaceFirst("-", "Option:"));
            });
        }
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        AddProductController.exportAnchorPane.getChildren().clear();
        AddProductController.exportAnchorPane.getChildren().add(AddProductController.exportPane);
        listOfCombinations.clear();
    }

    @FXML
    void finishButtonAction(ActionEvent event) {

        for (int i = 0; i < priceTextFields.size(); i++) {
            System.out.println(priceTextFields.get(i).getText());
            System.out.println(quantityTextFields.get(i).getText());

        }
        addDataToObject();
        ProductService productService = new ProductService(product);
        productService.start();

        productService.setOnRunning(e1 -> {
            secondPageAP.getChildren().add(loading);
        });
        productService.setOnSucceeded(e2 -> {

            secondPageAP.getChildren().remove(loading);
        });
        productService.setOnFailed(e3 -> {
            secondPageAP.getChildren().remove(loading);
        });
    }

    void addDataToObject() {
        ArrayList<Map<String, String>> combinations = new ArrayList<Map<String, String>>();  

        for (int k = 0; k < listOfCombinations.size(); k++) {
            combinationsList.add(new ArrayList<String>());
            variantsList.add(new ArrayList<String>());
            combinations.add(new HashMap<String, String>());
        }

        for (int k = 0; k < listOfCombinations.size(); k++) {
            for (int j = 0; j < listOfCombinations.get(k).size(); j++) {
            combinations.get(k).put(variantNames.get(j),listOfCombinations.get(k).get(j));
            
        }
            combinations.get(k).put("stock", quantityTextFields.get(k).getText());
            combinations.get(k).put("price", priceTextFields.get(k).getText());
            combinations.get(k).put("photo", photoPathList.get(k));

        }
        float discount_pt = 0;
         product.setName(productName);
         product.setDescription(productDesc);
         product.setCategoryId(AddProductController.categoryId);
         product.setUserId(user.getId());

        for (int i= 0; i<variantNames.size(); i++){
            Variant variants = new Variant();
            variants.setData(variantNames.get(i), oprionsList.get(i));
            System.out.println(variants);
            product.variant.add(variants);
        }


        product.setDiscount_pt(discount_pt);
        product.setCombination(combinations);
 

    }



}

