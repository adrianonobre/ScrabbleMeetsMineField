package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-01.
 */
public class ConsolePrinter {
    private final Board board;
    private final boolean showMines;

    public ConsolePrinter(Board board) {
        this(board, false);
    }

    public ConsolePrinter(Board board, boolean showMines) {
        this.board = board;
        this.showMines = showMines;
    }

    public void print() {
        final int rowCount = board.getRowCount();
        final int colCount = board.getColCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                final Square square = board.getSquare(row, col);
                final String element;
                if (SquareHelper.containsLetter(square)) {
                    element = "" + ((SquareContent.Letter) square.getContent()).getLetter();
                } else if (showMines && (square.getContent() instanceof SquareContent.Mine)) {
                    element = "@";
                } else {
                    element = "□";
                }
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
