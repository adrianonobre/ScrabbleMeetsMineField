package org.adrianonobre.scrabble;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by adriano on 2016-12-26.
 */
public class Main {

    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("must specify dictionary file");
            System.exit(1);
        }

        final String dictFile = args[0];
        Set<String> dictWords = new HashSet<>();
        try (Stream<String> stream = Files.lines(Paths.get(dictFile))) {
            dictWords = stream
                    .map(String::toUpperCase)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            System.err.println("Could not read dict file " + dictFile);
            System.exit(1);
        }

        final Game game = new Game(dictWords);
        game.start(10, 10);

        makePlay(game, 2, 2, Game.Orientation.VERTICAL, "air");
        makePlay(game, 0, 3, Game.Orientation.VERTICAL, "ar");
        makePlay(game, 3, 4, Game.Orientation.HORIZONTAL, "lift");
        makePlay(game, 3, 1, Game.Orientation.HORIZONTAL, "air");
    }

    private static void makePlay(Game game, int row, int col, Game.Orientation orientation, String word) {
        System.out.println("Play:");
        System.out.println(String.format("row: %d  col: %d  orientation: %s  word: %s", row, col, orientation, word));
        System.out.println(game.play(row, col, orientation, word));
        System.out.println(String.format("Score: " + game.getScore()));
        new ConsolePrinter(game.getBoard()).print();
        System.out.println();
    }
}
