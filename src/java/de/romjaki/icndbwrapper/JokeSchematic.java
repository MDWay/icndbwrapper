package de.romjaki.icndbwrapper;

/**
 * Created by RGR on 08.06.2017.
 */
public class JokeSchematic extends GsonSchematic<Joke> {

    public Joke value;

    @Override
    public Joke result() {
        return value;
    }
}
