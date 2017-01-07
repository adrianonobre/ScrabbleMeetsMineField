package org.adrianonobre.scrabble;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adriano on 2016-12-26.
 */
public class Demo {

    public static void main(String args[]) {
        Set<String> dictWords = new HashSet();
        dictWords.add("air");
        dictWords.add("lift");
        dictWords.add("airlift");
        dictWords.add("airport");

        final Game game = new Game(dictWords);
        game.start(9, 9);

        final ConsolePrinter consolePrinter = new ConsolePrinter(game.getBoard(), true);
        Driver.makePlay(game, 1, 2, Orientation.HORIZONTAL, "air", consolePrinter);
        Driver.makePlay(game, 1, 5, Orientation.HORIZONTAL, "lift", consolePrinter);
        Driver.makePlay(game, 0, 3, Orientation.VERTICAL, "airport", consolePrinter);
    }

}
