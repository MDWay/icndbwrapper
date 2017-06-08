package de.romjaki.icndbwrapper;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;


public class RestAction<T> {

    private final GsonSchematic<T> emptySchematic;
    private final String url;
    private final Map<String, String> parameters;

    public RestAction(GsonSchematic<T> emptySchematic, String relativeUrl, Map<String, String> parameters) {
        this.emptySchematic = emptySchematic;
        this.url = relativeUrl;
        this.parameters = parameters;
    }

    public static String executeRequest(String url, Map<String, String> parameters, String method) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(ICNDB.API_URL + url).openConnection();
        con.setRequestMethod(method);
        con.setUseCaches(false);
        parameters.forEach(con::addRequestProperty);
        Scanner s = new Scanner(con.getInputStream());
        s.useDelimiter("\\A");
        return s.next();
    }

    public Thread queue() {
        return queue(null);
    }

    public Thread queue(Consumer<T> success) {
        return queue(success, null);
    }

    public Thread queue(Consumer<T> success, Consumer<T> fail) {
        Thread t = new Thread(() -> {
            Gson gson = new Gson();
            GsonSchematic<T> schematic = null;
            try {
                schematic = gson.fromJson(executeGet(RestAction.this.url, RestAction.this.parameters), emptySchematic.getClass());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (schematic.succeeded()) {
                success.accept(schematic.result());
            }
        });
        t.start();
        return t;
    }

    private String executeGet(String url, Map<String, String> parameters) throws IOException {
        return executeRequest(url, parameters, "GET");
    }

    @SuppressWarnings({"unchecked"})
    public T complete() {
        try {
            Object[] outside = new Object[1];
            queue(a -> outside[0] = a).join();
            return (T) outside[0];
        } catch (InterruptedException e) {
        }
        return null;
    }

}
