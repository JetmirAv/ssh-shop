package org.fiek.services.auth;
import com.google.gson.JsonObject;
import eu.lestard.fluxfx.View;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.fiek.utils.Ajax;


public class PasswordService extends Service<Void> implements View {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public PasswordService(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                JsonObject json = new JsonObject();
                json.addProperty("oldPassword", oldPassword);
                json.addProperty("newPassword", newPassword);
                json.addProperty("confirmPassword", confirmPassword);
                try {
                    Ajax request = new Ajax();
                    JsonObject response = request.post("/auth/change-password", json.toString());
                } catch (Exception exception) {
                    this.cancel();
                    exception.printStackTrace();
                }
                return null;
            };
        };
    }
}
