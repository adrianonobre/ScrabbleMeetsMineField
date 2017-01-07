package org.adrianonobre;

import org.adrianonobre.scrabble.Board;
import org.adrianonobre.scrabble.SquareContent;

/**
 * Created by adriano on 2017-01-07.
 */
public class BoardTestHelper {
    public static Board getBoardWithSetState(String[] boardState) {
        final int rowCount = boardState.length;
        final int colCount = boardState[0].length();

        final Board board = new Board(rowCount, colCount);
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                final char letter = boardState[r].charAt(c);
                if (letter >= 'A' && letter <= 'Z') {
                    board.getSquare(r, c).setContent(new SquareContent.Letter(letter));
                }
            }
        }
        return board;
    }
}
