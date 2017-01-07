package org.adrianonobre.scrabble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by adriano on 2017-01-07.
 */
public class Driver {

    public static final int BOARD_ROW_COUNT = 9;
    public static final int BOARD_COL_COUNT = 9;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("must specify dictionary file");
            System.exit(1);
        }

        final String dictFile = args[0];
        Set<String> dictWords = new HashSet<>();
        try (Stream<String> stream = Files.lines(Paths.get(dictFile))) {
            dictWords = stream.collect(Collectors.toSet());
        } catch (IOException e) {
            System.err.println("Could not read dict file " + dictFile);
            System.exit(1);
        }

        final Game game = new Game(dictWords);
        game.start(BOARD_ROW_COUNT, BOARD_COL_COUNT);

        Scanner scanner = new Scanner(System.in);

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        final ConsolePrinter consolePrinter = new ConsolePrinter(game.getBoard());
        consolePrinter.print();
        while (true) {
            System.out.print("word: ");
            String word = getInput(br);
            System.out.print("orientation (v/h): ");
            String o = getInput(br);
            Orientation orientation = o.toUpperCase().startsWith("V") ? Orientation.VERTICAL : Orientation.HORIZONTAL;
            System.out.print("row (0-" + BOARD_ROW_COUNT + "): ");
            final Integer row = Integer.valueOf(getInput(br));
            System.out.print("col (0-" + BOARD_COL_COUNT + "): ");
            final Integer col = Integer.valueOf(getInput(br));

            makePlay(game, row, col, orientation, word, consolePrinter);
        }
    }

    private static String getInput(BufferedReader br) throws IOException {
        final String input = br.readLine();
        if (input.trim().length() == 0) {
            System.exit(0);
        }
        return input;
    }

    static void makePlay(Game game, int row, int col, Orientation orientation, String word, ConsolePrinter consolePrinter) {
        System.out.println("Play:");
        System.out.println(String.format("row: %d  col: %d  orientation: %s  word: %s", row, col, orientation, word));
        System.out.println(game.play(row, col, orientation, word));
        System.out.println(String.format("Score: " + game.getScore()));
        consolePrinter.print();
        System.out.println();
    }
}
