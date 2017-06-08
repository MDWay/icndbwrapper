package de.romjaki.icndbwrapper.test;

import de.romjaki.icndbwrapper.ICNDB;
import org.junit.Test;

/**
 * Created by RGR on 08.06.2017.
 */
public class RandomJokeTest {

    @Test
    public void downloadRandomJokeUsingQueue() {
        ICNDB icndb = new ICNDB();
        icndb.getRandomJoke().queue();
    }


}
