package de.romjaki.icndbwrapper;

import com.google.gson.Gson;

import java.util.Map;
import java.util.function.Consumer;


public class RestAction<T> {

    private final GsonSchematic<T> emptySchematic;
    private final String url;
    private final Map<String, String> parameters;

    public RestAction(R emptySchematic, String relativeUrl, Map<String, String> parameters) {
        this.emptySchematic = emptySchematic;
        this.url = relativeUrl;
        this.parameters = parameters;
    }

    public static String executeGet(String url, Map<String, String> parameters) {
        return "";
    }

    public void queue() {
        queue(null);
    }

    public void queue(Consumer<T> success) {
        queue(success, null);
    }

    public void queue(Consumer<T> success, Consumer<T> fail) {
        Thread t = new Thread(() -> {
            Gson gson = new Gson();
            R schematic =  gson.fromJson(executeGet(RestAction.this.url, RestAction.this.parameters), emptySchematic.getClass());
        });
    }

    public T complete() {
        // TODO
    }

}
