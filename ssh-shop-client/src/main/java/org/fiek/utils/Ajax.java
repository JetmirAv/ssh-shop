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
import org.fiek.App;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

public class Ajax {

    EasyDI easyDI = App.context;

    final BaseStore baseStore;

    private final String host = "http://192.168.1.67:5000/";


    public Ajax() {
        this.baseStore = easyDI.getInstance(BaseStore.class);
    }


    public JsonObject post(String route, String data) throws Exception {
        String result = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost request = new HttpPost(this.host + route);
            addHeaders(request);

            request.setEntity(new StringEntity(data));
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

        JsonObject object = JsonParser.parseString(result).getAsJsonObject();
        return object;
    }

    public JsonObject patch(String route, String data) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPatch request = new HttpPatch(this.host + route);
            addHeaders(request);

            request.setEntity(new StringEntity(data));
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


    public JsonObject get(String route) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(this.host + route);
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
        JsonObject object = JsonParser.parseString(result).getAsJsonObject();
        return object;
    }

    private HttpEntity getResponse(CloseableHttpResponse response) throws Exception {
        switch (response.getStatusLine().getStatusCode()) {
            case 200:
                return response.getEntity();
            case 500:
                String error = "{ \"message\": \"Server Error\" }";
                ErrorHandler.handle(error);
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
        AuthStore authStore = baseStore.getAuthStore();
        if (authStore != null)
            request.addHeader("Authorization", authStore.getToken() == null ? "" : "Bearer " + authStore.getToken());
        request.addHeader("content-type", "application/json");
        request.addHeader("UserAgent", "Mozilla");
        request.addHeader("Connection", "keep-alive");
    }

}
