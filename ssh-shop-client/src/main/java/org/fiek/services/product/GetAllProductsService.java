package org.fiek.services.product;

import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.store.chat.AddChannelsAction;
import org.fiek.store.product.FetchProductsAction;
import org.fiek.utils.Ajax;

public class GetAllProductsService extends Service<Void> implements View {

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.get("/products");
                    String rows = response.get("products").toString();

                    System.out.println("Call: " + rows);

                    publishAction(new FetchProductsAction(rows));

                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            }
        };
    }

}
