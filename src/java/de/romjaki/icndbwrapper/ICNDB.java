package de.romjaki.icndbwrapper;

import java.util.HashMap;
import java.util.Map;

public class ICNDB {
    public ICNDB() {

    }

    public static final String API_URL = "api.icndb.com/";

    public RestAction<Joke> getRandomJoke() {
        Map<String, String> map = new HashMap<>();
        return new RestAction<>(new JokeSchematic(), "jokes/random", map);
    }

}
