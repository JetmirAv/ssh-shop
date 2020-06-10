package org.fiek.services.product;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.controllers.profile.ListCardController;
import org.fiek.models.*;
import org.fiek.store.auth.CountryAction;
import org.fiek.store.auth.GetCountryByCityAction;
import org.fiek.store.auth.GetCountryByNameAction;
import org.fiek.utils.Ajax;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

public class GetProductDetailService extends Service<Void> implements View {


        ArrayList<ArrayList<String>> nodes ;


    private void getProductDetails() throws Exception {
        System.out.println("Brenda  produktit!");
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("products/5edfbfab9876ea4bd8af6efb");
        JsonElement entry = response.getAsJsonObject("user").getAsJsonArray("variants");
        String str = entry.toString();
        System.out.println("Array is : " + entry);

        final Variant[] variants = new GsonBuilder().create().fromJson(entry, Variant[].class);
        for (Variant vObj : variants) {
            System.out.println("Variant objekti:" + vObj);
        }
        // Ky i merr kejt kombinimet
        JsonArray entryKryesor = response.getAsJsonObject("user").getAsJsonArray("combinations");
        // Ky e merr vetem objektin e par te kombinimev
        JsonObject entry1 = response.getAsJsonObject("user").getAsJsonArray("combinations").get(1).getAsJsonObject();

        System.out.println("entry1:" + entry1);
        final Combinations[] combinations = new GsonBuilder().create().fromJson(entry1, Combinations[].class);






        }



    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getProductDetails();
                return null;
            }
        };
    }
}
