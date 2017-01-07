package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-05.
 */
public class SquareHelper {
    public static boolean containsLetter(Square square) {
        return square.getContent() instanceof SquareContent.Letter && ((SquareContent.Letter) square.getContent()).getLetter() != null;
    }
}
