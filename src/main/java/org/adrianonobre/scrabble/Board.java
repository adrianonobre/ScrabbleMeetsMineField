package org.adrianonobre.scrabble;

import java.util.Iterator;

/**
 * Created by adriano on 2016-12-27.
 */
public class Board {

    private Square[][] squares;

    public void init(int rowCount, int colCount) {
        this.squares = new Square[rowCount][];

        for (int r = 0; r < rowCount; r++) {
            squares[r] = new Square[colCount];

            for (int c = 0; c < colCount; c++) {
                squares[r][c] = new Square();
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

    public BoardIterator iterator(int row, int col, Game.Orientation orientation) {
        return orientation == Game.Orientation.HORIZONTAL
                       ? new HorizontalIterator(this, row, col)
                       : new VerticalIterator(this, row, col);
    }

    public Object clone() {
        final Board clone = new Board();
        int rowCount = squares.length;
        int colCount = squares[0].length;

        clone.squares = new Square[rowCount][];
        for (int r = 0; r < rowCount; r++) {
            clone.squares[r] = new Square[colCount];

            for (int c = 0; c < colCount; c++) {
                clone.squares[r][c] = (Square) squares[r][c].clone();
            }
        }

        return clone;
    }

    public class Square {

        private String currElement;
        private boolean hasMine;

        public Square() {
            currElement = null;
        }

        public String getCurrElement() {
            return currElement;
        }

        public void setCurrElement(String currElement) {
            this.currElement = currElement;
        }

        public boolean hasMine() {
            return hasMine;
        }

        public void setHasMine(boolean hasMine) {
            this.hasMine = hasMine;
        }

        public Object clone() {
            final Square clone = new Square();
            clone.currElement = currElement;
            clone.hasMine = hasMine;
            return clone;
        }
    }

    interface BoardIterator extends Iterator<Square> {

        int getCurrRow();

        int getCurrCol();

        boolean hasPrevious();

        Square previous();

        Square current();
    }

    class VerticalIterator implements BoardIterator {
        protected final Board board;
        protected int nextRow;
        protected int previousRow;
        private final int col;

        VerticalIterator(Board board, int initialRow, int initialCol) {
            this.board = board;
            nextRow = initialRow;
            previousRow = initialRow;
            col = initialCol;
        }

        @Override
        public int getCurrRow() {
            return (nextRow + previousRow) / 2;
        }

        @Override
        public int getCurrCol() {
            return col;
        }

        @Override
        public boolean hasPrevious() {
            return previousRow >= 0 && col < board.getColCount();
        }

        @Override
        public Square previous() {
            final Square previous = board.getSquare(previousRow, col);
            if (nextRow == previousRow) {
                nextRow++;
            } else {
                nextRow--;
            }
            previousRow--;
            return previous;
        }

        @Override
        public Square current() {
            return board.getSquare(getCurrRow(), getCurrCol());
        }

        @Override
        public boolean hasNext() {
            return nextRow < board.getRowCount() && col < board.getColCount();
        }

        @Override
        public Square next() {
            final Square next = board.getSquare(nextRow, col);
            if (previousRow == nextRow) {
                previousRow--;
            } else {
                previousRow++;
            }
            nextRow++;
            return next;
        }
    }

    class HorizontalIterator implements BoardIterator {
        protected final Board board;
        protected final int row;
        protected int nextCol;
        protected int previousCol;

        public HorizontalIterator(Board board, int initialRow, int initialCol) {
            this.board = board;
            row = initialRow;
            nextCol = initialCol;
            previousCol = initialCol;
        }

        @Override
        public int getCurrRow() {
            return row;
        }

        @Override
        public int getCurrCol() {
            return (previousCol + nextCol) / 2;
        }

        @Override
        public boolean hasPrevious() {
            return row < board.getRowCount() && previousCol >= 0;
        }

        @Override
        public Square previous() {
            final Square previous = board.getSquare(row, previousCol);
            if (nextCol == previousCol) {
                nextCol++;
            } else {
                nextCol--;
            }
            previousCol--;
            return previous;
        }

        @Override
        public Square current() {
            return board.getSquare(getCurrRow(), getCurrCol());
        }

        @Override
        public boolean hasNext() {
            return row < board.getRowCount() && nextCol < board.getColCount();
        }

        @Override
        public Square next() {
            final Square next = board.getSquare(row, nextCol);
            if (previousCol == nextCol) {
                previousCol--;
            } else {
                previousCol++;
            }
            nextCol++;
            return next;
        }
    }
}
