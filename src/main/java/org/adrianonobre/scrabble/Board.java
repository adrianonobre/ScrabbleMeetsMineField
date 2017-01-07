package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2016-12-27.
 */
public class Board {

    private Square[][] squares;

    public Board(int rowCount, int colCount) {
        this.squares = new Square[rowCount][];

        for (int r = 0; r < rowCount; r++) {
            squares[r] = new Square[colCount];

            for (int c = 0; c < colCount; c++) {
                squares[r][c] = new Square(this, r, c);
            }
        }
    }

    public int getRowCount() {
        return squares.length;
    }

    public int getColCount() {
        return squares[0].length;
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

}
