package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-01.
 */
class ConsolePrinter {
    private final Board board;

    ConsolePrinter(Board board) {
        this.board = board;
    }

    public void print() {
        final int rowCount = board.getRowCount();
        final int colCount = board.getColCount();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                final Board.Square square = board.getSquare(row, col);
                final String element;
                if (square.getCurrElement() != null) {
                    element = square.getCurrElement();
                } else if (square.hasMine()) {
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
