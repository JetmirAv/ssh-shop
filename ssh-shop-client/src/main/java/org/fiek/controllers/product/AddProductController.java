package org.fiek.controllers.product;

import com.jfoenix.controls.JFXComboBox;


import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.fiek.App;

public class AddProductController {
    public ArrayList<JFXTextField> variantNameList = new ArrayList<JFXTextField>();
    public ArrayList<ArrayList<JFXTextField>> lists = new ArrayList<ArrayList<JFXTextField>>();
    public ArrayList<ArrayList<String>> listOfCombinations = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> listToExport = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> variantNamesToExport = new ArrayList<String>();


    public int t=0;
    public int i=1;

    @FXML // fx:id="variantButtons"
    private HBox variantButtons; // Value injected by FXMLLoader

    @FXML // fx:id="category_id"
    private JFXComboBox<?> category_id; // Value injected by FXMLLoader

    @FXML // fx:id="variantsVBox"
    private VBox variantsVBox; // Value injected by FXMLLoader

    @FXML // fx:id="variantButtonsHBox"
    private HBox variantButtonsHBox; // Value injected by FXMLLoader

    @FXML // fx:id="addVariant1"
    private Button addVariant1; // Value injected by FXMLLoader

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="nextButton"
    private Button nextButton; // Value injected by FXMLLoader

    @FXML // fx:id="addProduct1"
    private AnchorPane addProduct1; // Value injected by FXMLLoader

    @FXML // fx:id="mainPaneAddProduct1"
    private Pane mainPaneAddProduct1;

    private final Button removeVariantBtn = new Button("Remove variant");


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert variantButtons != null : "fx:id=\"variantButtons\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert category_id != null : "fx:id=\"category_id\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert variantsVBox != null : "fx:id=\"variantsVBox\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert variantButtonsHBox != null : "fx:id=\"variantButtonsHBox\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert addVariant1 != null : "fx:id=\"addVariant1\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'add-product-1.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'add-product-1.fxml'.";

    }
    @FXML
    void addVariant(ActionEvent event) {

        HBox hBox = new HBox();
        VBox vBox = new VBox();
        HBox hBox2 = new HBox();
        Button button = new Button("+");
        Button remove = new Button("-");
        FlowPane flowPane = new FlowPane();
        JFXTextField textField = new JFXTextField();
        JFXTextField variantName = new JFXTextField();
        ArrayList<JFXTextField> variantList = new ArrayList<JFXTextField>();

        hBox.getChildren().add(vBox);
        vBox.getChildren().add(hBox2);
        vBox.getChildren().add(flowPane);
        hBox2.getChildren().add(variantName);
        flowPane.getChildren().add(textField);
        flowPane.getChildren().add(button);
        variantsVBox.getChildren().add(hBox);
        variantList.add(textField);

        vBox.setSpacing(10);
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        textField.getStyleClass().add("add-product-option-fields");
        variantName.getStyleClass().add("variant-name");
        flowPane.getStyleClass().add("flow-pane-properties");
        button.getStyleClass().add("add-circle-button");
        remove.getStyleClass().add("remove-circle-button");

        button.setOnAction(e -> {
            addOptions(flowPane, button, remove, variantList);
        });
        remove.setOnAction(e -> {
            removeOptions(flowPane, remove, variantList);
        });
        lists.add(variantList);
        if (lists.size()>1 && i == 1){
            variantButtonsHBox.getChildren().add(removeVariantBtn);
            removeVariantBtn.getStyleClass().add("varinatButonsProperties");
            removeVariantBtn.getStyleClass().add("removeVarinatProperties");
            removeVariantBtn.setOnAction(e ->{
                removeVariant();
            });
            i=0;
        }
        variantNameList.add(variantName);

    }
    void removeVariant() {
        int listIndex;
        listIndex = lists.size()-1;
        lists.remove(listIndex);
        variantNameList.remove(listIndex);
        variantsVBox.getChildren().remove(listIndex);
        if(lists.size() == 1){
            variantButtonsHBox.getChildren().remove(removeVariantBtn);
            i=1;
        }
    }


    void addOptions(FlowPane flowPane, Button button,Button button1, ArrayList<JFXTextField> variantList) {

        JFXTextField textField = new JFXTextField();
        textField.getStyleClass().add("add-product-option-fields");
        flowPane.getChildren().remove(button);
        flowPane.getChildren().remove(button1);
        flowPane.getChildren().add(textField);
        flowPane.getChildren().add(button);
        flowPane.getChildren().add(button1);
        variantList.add(textField);

    }

    void removeOptions(FlowPane flowPane, Button button1, ArrayList<JFXTextField> variantList) {
          int textFieldToRemmove;
          flowPane.getChildren().remove(variantList.get(variantList.size()-1));
          textFieldToRemmove = variantList.size()-1;
          variantList.remove(textFieldToRemmove);
          if(variantList.size() == 1){
              flowPane.getChildren().remove(button1);
          }
    }


    @FXML
    void nextToSecondPage(ActionEvent event) throws IOException {
        int arrayListNumber=1;

        for (int i = 0; i < lists.size(); i++) {
            arrayListNumber = arrayListNumber * lists.get(i).size();
        }

        for (int t=0; t<arrayListNumber; t++ ){
            listOfCombinations.add(new ArrayList<String>());
        }

        for (int y=0 ; y<lists.size(); y++){
            for (int j=0; j<lists.get(y).size(); j++) {
                for (int n =combinationStartIndex(lists,y, j) ; n < listOfCombinations.size(); n=n + combinationIndex(lists, y)) {
                    listOfCombinations.get(n).add(lists.get(y).get(j).getText());
                }
                t=0;
            }
        }

        for (int i=0; i<listOfCombinations.size(); i++){
            listToExport.add(listOfCombinations.get(i));
        }

        for (int k=0; k<variantNameList.size();k++){
            variantNamesToExport.add(variantNameList.get(k).getText());
        }



        addProduct1.getChildren().remove(mainPaneAddProduct1);

        AnchorPane anchorPane1;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/product/add-product-2.fxml"));
        fxmlLoader.setController(new AddProductController1());
        anchorPane1 = fxmlLoader.load();
        addProduct1.getChildren().add(anchorPane1);
    }

        int combinationIndex(ArrayList<ArrayList<JFXTextField>> arrayList, int i){
        int k=1;

            if(i == arrayList.size()-1){
                return arrayList.get(arrayList.size()-1).size();
            }
            else {
                for (int a=i+1; a<arrayList.size(); a++){
                    k = k * arrayList.get(a).size();
                }
                if (t<(k-1)) {
                    t=t+1;
                    return 1;
                }
                else if (k==1){
                    return arrayList.get(i).size();
                }
                else {
                    t = 0;
                    if (arrayList.get(i).size() == 1) {
                        return 1;
                    } else {
                        return (k ) * (arrayList.get(i).size() - 1)+1;

                    }
                }
            }
        }
    int combinationStartIndex(ArrayList<ArrayList<JFXTextField>> arrayList, int i, int j) {
        int k = 1;
            for (int a = i + 1; a < arrayList.size(); a++) {
                k = k * arrayList.get(a).size();
            }
            if (j != 0) {
                return j * k ;
            } else {
                return 0;
            }
    }

}
