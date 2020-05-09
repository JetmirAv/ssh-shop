package org.fiek.utils.helpers;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import com.google.gson.GsonBuilder;

public class JsonHelper<T> {
    final Class<T> type;

    public JsonHelper(Class<T> type) {
        this.type = type;
    }

    public String serializeJson(T model, String path) throws Exception {
        String serialized = new GsonBuilder().create().toJson(model, this.type);
        FileWriter fw = new FileWriter(path, false);
        fw.write(serialized);
        fw.close();

        return serialized;
    }

    public T deserializeJson(String path) throws Exception {
        T model = null;

        Scanner fr = new Scanner(new File(path));
        StringBuilder sb = new StringBuilder();
        while (fr.hasNext()) {
            sb.append(fr.next());
        }
        fr.close();

        String json = sb.toString();
        model = new GsonBuilder().create().fromJson(json, this.type);

        return model;
    }
}