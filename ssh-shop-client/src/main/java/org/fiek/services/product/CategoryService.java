package org.fiek.services.product;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.models.Category;
import org.fiek.models.Country;
import org.fiek.utils.Ajax;
import java.lang.System;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CategoryService extends Service<Void> implements View {
    public static ArrayList<Category> categories = new ArrayList<>();
    private void getcategories() throws Exception {
        if(categories == null || categories.isEmpty()){
            Ajax request = new Ajax();
            JsonObject response = request.getAsJson("/categories");
            String jsonCategories = response.get("categories").toString();
            

            Category[] categories1 = new GsonBuilder().create().fromJson(jsonCategories, Category[].class);
            categories.addAll(Arrays.asList(categories1));

        }
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                getcategories();
                return null;
            }
        };
    }
}