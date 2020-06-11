package org.fiek.services.product;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.fiek.models.Product;
import org.fiek.models.User;
import org.fiek.store.auth.AddTokenAction;
import org.fiek.store.product.AddProductAction;
import org.fiek.utils.Ajax;
import org.fiek.utils.Tuple;

import java.io.IOException;
import java.util.ArrayList;

public class ProductService extends Service<Void> implements View{
    private Product product;

    public ProductService (Product product){
        this.product = product;
    }


    public void addProduct() throws Exception {

        final String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this.product, Product.class);
        System.out.println(json);

        Ajax request = new Ajax();
        JsonObject response = request.post("products", json);

//        String jsonProduct = response.get("products").toString();
//        publishAction(new AddProductAction(jsonProduct));
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                addProduct();
                return null;
            }
        };
    }
}
