package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-05.
 */
public class SquareHelper {
    public static boolean containsLetter(Cell cell) {
        return cell.getContent() instanceof SquareContent.Letter && ((SquareContent.Letter) cell.getContent()).getLetter() != null;
    }
}
