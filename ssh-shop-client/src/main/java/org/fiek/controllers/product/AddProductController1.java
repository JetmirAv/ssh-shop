package org.fiek.controllers.product;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.fiek.utils.ImageUploadHandler;
import org.fiek.utils.Tuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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

        @FXML // fx:id="optionN"
        private Button optionN; // Value injected by FXMLLoader

        @FXML // fx:id="optionElements"
        private Label optionElements; // Value injected by FXMLLoader

        @FXML // fx:id="mainPhotoIV"
        private ImageView mainPhotoIV; // Value injected by FXMLLoader

        @FXML // fx:id="priceJTF"
        private JFXTextField priceJTF; // Value injected by FXMLLoader

        @FXML // fx:id="quantityJTF"
        private JFXTextField quantityJTF; // Value injected by FXMLLoader

        @FXML // fx:id="addPhotoButton"
        private Button addPhotoButton; // Value injected by FXMLLoader

        @FXML // fx:id="backButton"
        private Button backButton; // Value injected by FXMLLoader

        @FXML // fx:id="finishButton"
        private Button finishButton; // Value injected by FXMLLoader

        public Button combinationButtons = new Button("Option");
        private ArrayList<Button> buttonsList = new ArrayList<Button>();
        private ArrayList<ArrayList<String>> listOfCombinations;
        private ArrayList<String> variantNames;
        @FXML
            // This method is called by the FXMLLoader when initialization is complete
        void initialize() throws IOException {
            assert secondPageVBox != null : "fx:id=\"secondPageVBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert optionsHBox != null : "fx:id=\"optionsHBox\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert optionN != null : "fx:id=\"optionN\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert optionElements != null : "fx:id=\"optionElements\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert mainPhotoIV != null : "fx:id=\"mainPhotoIV\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert priceJTF != null : "fx:id=\"priceJTF\" was not injected: check your FXML file 'add-product-2.fxml'.";
            assert quantityJTF != null : "fx:id=\"quantityJTF\" was not injected: check your FXML file 'add-product-2.fxml'.";
            //secondPageAP.getChildren().remove(secondPageVBox);

            listOfCombinations = AddProductController.listToExport;
            variantNames = AddProductController.variantNamesToExport;
            getOptions(listOfCombinations);
            for (int i=0; i<listOfCombinations.size();i++){
                for (int j=0; j<listOfCombinations.get(i).size(); j++){
                }
                System.out.println();
            }
            buttonEvents();
        }

        public void getOptions(ArrayList<ArrayList<String>> listOfCombinations) throws IOException {
        for (int i=0; i<listOfCombinations.size(); i++){
             Button combinationButtons = new Button("Option " + i);
             optionsHBox.getChildren().add(combinationButtons);
             buttonsList.add(combinationButtons);
             combinationButtons.getStyleClass().add("combinationOptions");
             }
        }
        public  ArrayList<JFXTextField> test = new ArrayList<JFXTextField>();
        void buttonEvents(){

            //ListIterator<Button> iterator = buttonsList.iterator();
            for (int i=0; i<buttonsList.size(); i++){
                int finalI = i;
                //secondPageAP.getChildren().remove(secondPageVBox);
                buttonsList.get(i).setOnAction(e->{
                  //  secondPageAP.getChildren().add(secondPageVBox);
                    StringBuilder options = new StringBuilder();
                    for (int j = 0; j < listOfCombinations.get(finalI).size(); j++) {
                        try {
                            System.out.println(addPhotoEvent());
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                        options.append(" - " + listOfCombinations.get(finalI).get(j));
                        }

                        optionElements.setText(options.toString().replaceFirst("-", "Option:"));
                    test.add(priceJTF);

                });
            }
        }

        String addPhotoEvent() throws FileNotFoundException{
            StringBuilder photoName = new StringBuilder();
            addPhotoButton.setOnAction(e->{
                ImageUploadHandler imageUploadHandler = new ImageUploadHandler();
                try {
                    Tuple<Image,String> costumImage;
                    costumImage = imageUploadHandler.uploadImage();
                    mainPhotoIV.setImage(costumImage.getFirst());
                    mainPhotoIV.setPreserveRatio(false);
                    photoName.append(costumImage.getSecond());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            });
            return photoName.toString();
        }


}

