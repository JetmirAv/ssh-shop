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
import org.reactfx.value.Var;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class GetProductDetailService extends Service<Void> implements View {

    // KOMBINIMET
    public static Map<String, Object> attributes = new HashMap<String, Object>();;
    // VARIANTET
    public static Variant[] variants2 ;
    // EMRI I PRODUKTIT
    public static String productName;

    private void getProductDetails() throws Exception {
        Ajax request = new Ajax();
        JsonObject response = request.getAsJson("products/5edfbfab9876ea4bd8af6efb");
        JsonElement variantElement = response.getAsJsonObject("user").getAsJsonArray("variants");
        String str = variantElement.toString();

        final Variant[] variants = new GsonBuilder().create().fromJson(variantElement, Variant[].class);
        for (Variant vObj : variants) {
            System.out.println("Variant objekti:" + vObj);
        }

        variants2 = variants;
        // Ky i merr kejt kombinimet
        JsonArray entryKryesor = response.getAsJsonObject("user").getAsJsonArray("combinations");

        // Ky e merr vetem objektin e par te kombinimev
        JsonObject entry1 = response.getAsJsonObject("user").getAsJsonArray("combinations").get(1).getAsJsonObject();

        productName = response.getAsJsonObject("user").get("name").toString();

        Set<Map.Entry<String, JsonElement>> entrySet = entry1.entrySet();

        for (Map.Entry<String,JsonElement> entry2 : entrySet){
            attributes.put(entry2.getKey(), entry1.get(entry2.getKey()));

        }


        Set<String> keys = attributes.keySet();

        // Iterate through Map
        for(String key : keys ) {
            System.out.println(key + ": " + attributes.get(key));
        }
        // Iterate through map
        for (Map.Entry<String, Object> iterate : attributes.entrySet()) {
            System.out.println("1 ::" + iterate.getKey() + "/" + iterate.getValue());
        }

        // Get Specific Value
        Object value = attributes.get("photo");
        System.out.println("Value : " + value.toString());

        Combinations combinations = new Combinations(attributes);
        System.out.println(combinations);


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
