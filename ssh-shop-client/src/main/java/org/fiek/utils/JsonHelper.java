package org.fiek.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.GsonBuilder;

public class JsonHelper<T> {
    final Class<T> type;

    public JsonHelper(Class<T> type) {
        this.type = type;
    }

    public String serializeJson(T model) throws Exception {
        String serialized = new GsonBuilder().create().toJson(model, this.type);
        return serialized;
    }

    public T deserializeJson(String json) throws Exception {
        return new GsonBuilder().create().fromJson(json, this.type);
    }
}