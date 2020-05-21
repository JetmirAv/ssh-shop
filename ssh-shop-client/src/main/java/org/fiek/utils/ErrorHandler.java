package org.fiek.utils;

import com.google.gson.GsonBuilder;
import javafx.stage.Stage;
import org.fiek.App;
import org.fiek.models.Error;

public class ErrorHandler {

    public static Stage stage;

    public static void handle(String response){
        Error error = new GsonBuilder().create().fromJson(response, Error.class);


    }

}
