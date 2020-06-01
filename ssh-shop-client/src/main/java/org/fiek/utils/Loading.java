package org.fiek.utils;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Loading extends StackPane {

    public static final String ID = "progressIndicator";

    public Loading() {

        getStyleClass().add("backdrop");
        setId(ID);

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        progressIndicator.setMinHeight(150);
        progressIndicator.setMinWidth(150);

        getChildren().add(progressIndicator);

        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);

    }

    public void hide(){
        getChildren().clear();
        setVisible(false);
    }

    public void show(){

    }

}
