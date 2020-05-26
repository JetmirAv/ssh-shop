package org.fiek.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.lestard.easydi.EasyDI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

public class Ajax {

    EasyDI easyDI = new EasyDI();

    BaseStore baseStore = easyDI.getInstance(BaseStore.class);

    private final String host = "http://localhost:5000/";

    public static enum methods {GET, POST, PATCH, DELETE}

    final String route;
    final String method;
    final String data;

    public Ajax(String route, methods method, String data) {
        this.route = route;
        this.method = method.toString();
        this.data = data;
    }

    public JsonObject post() throws Exception {
        String result = "";

        System.out.println("Para post");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("MAs post");

        try {
            HttpPost request = new HttpPost(this.host + this.route);
            addHeaders(request);

            request.setEntity(new StringEntity(this.data));
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = getResponse(response);
                if (entity != null)
                    result = EntityUtils.toString(entity);
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                response.close();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            httpClient.close();
        }

        System.out.println("Kenj");

        JsonObject object = JsonParser.parseString(result).getAsJsonObject();
        return object;
    }

    public JsonObject patch() throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPatch request = new HttpPatch(this.host + this.route);
            addHeaders(request);

            request.setEntity(new StringEntity(this.data));
            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = getResponse(response);
                if (entity != null)
                    result = EntityUtils.toString(entity);

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }


        JsonObject object = JsonParser.parseString(result).getAsJsonObject();
        return object;
    }


    public String get() throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(this.host + this.route);
            addHeaders(request);

            CloseableHttpResponse response = httpClient.execute(request);

            try {
                HttpEntity entity = getResponse(response);
                if (entity != null)
                    result = EntityUtils.toString(entity);

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return result;
    }

    private HttpEntity getResponse(CloseableHttpResponse response) throws Exception {
        switch (response.getStatusLine().getStatusCode()) {
            case 200:
                return response.getEntity();
            case 500:
                throw new Exception("500");
            default:
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    ErrorHandler.handle(EntityUtils.toString(entity));
                }
                throw new Exception("Error");
        }
    }

    private void addHeaders(HttpRequestBase request) {
        System.out.println("Knej po ");
        AuthStore authStore = baseStore.getAuthStore();
        System.out.println("Knej nashta ");
        if (authStore != null) {
            System.out.println("Token Ajax: " + authStore.getToken());
            if (authStore.getUser() != null)
                System.out.println("USer Ajax" + authStore.getUser().getFirstName());
            request.addHeader("token", authStore.getToken() == null ? "" : "Bearer " + authStore.getToken());
        }
        System.out.println("ishalla jo knej");

        request.addHeader("content-type", "application/json");
        request.addHeader("UserAgent", "Mozilla");
        request.addHeader("Connection", "keep-alive");
    }

}
