package org.fiek.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.lestard.easydi.EasyDI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.fiek.App;
import org.fiek.store.BaseStore;
import org.fiek.store.auth.AuthStore;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

public class Ajax {

    EasyDI easyDI = App.context;

    final BaseStore baseStore;
    final SSLConnectionSocketFactory sslsf;
    final PoolingHttpClientConnectionManager cm;
    final CloseableHttpClient httpClient;
    private final String host = "http://192.168.43.67:5001/";


    public Ajax() {
        this.baseStore = easyDI.getInstance(BaseStore.class);
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(),
                    NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        cm = new PoolingHttpClientConnectionManager(registry);

        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm).build();
    }


    public JsonObject post(String route, String data) throws Exception {
        String result = "";

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

    public String  delete(String route) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete request = new HttpDelete(this.host + route);
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
    public JsonObject get(String route) throws Exception {
        String result = "";
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

    public JsonObject getAsJson(String route) throws Exception {
        String result = "";
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
