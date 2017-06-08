package de.romjaki.icndbwrapper;

/**
 * Created by RGR on 08.06.2017.
 */
public abstract class GsonSchematic<T> {

    public String type = "unknown_fail";

    public boolean succeeded() {
        return false;
    }

    public abstract T result();
}
