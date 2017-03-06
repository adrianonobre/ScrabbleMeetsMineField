package org.adrianonobre.scrabble;

import org.adrianonobre.scrabble.visitor.Visitor;

/**
 * Created by adriano on 2017-01-05.
 */
public class CellImpl implements Cell {

    private final BoardImpl board;
    private final int row;
    private final int col;
    private SquareContent previousContent;
    private SquareContent content;
    private boolean hasMine;

    @Override
    public BoardImpl getBoard() {
        return board;
    }

    public CellImpl(BoardImpl board, int row, int col) {
        content = null;
        this.board = board;
        this.row = row;
        this.col = col;
    }

    @Override
    public SquareContent getPreviousContent() {
        return previousContent;
    }

    @Override
    public Cell clone() {
        final CellImpl clone = new CellImpl(this.board, this.row, this.col);
        clone.previousContent = previousContent;
        clone.content = content;
        clone.hasMine = hasMine;
        return clone;
    }

    @Override
    public void accept(Visitor visitor, PlayOutcome playOutcome) {
        visitor.visitSquare(this, playOutcome);
    }

    @Override
    public SquareContent getContent() {
        return content;
    }

    @Override
    public void restoreContent() {
        this.content = this.previousContent;
    }

    @Override
    public void setContent(SquareContent content) {
        this.previousContent = this.content;
        this.content = content;
    }

    @Override
    public CellIterator horizontalIterator() {
        return new CellImpl.Horizontal(this);
    }

    @Override
    public CellIterator verticalIterator() {
        return new CellImpl.Vertical(this);
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    static class Vertical implements CellIterator {

        private CellImpl curr;

        private Vertical(CellImpl cell) {
            curr = cell;
        }

        @Override
        public boolean hasPrevious() {
            return curr.row > 0;
        }

        @Override
        public boolean hasNext() {
            return curr.row + 1 < curr.board.getRowCount();
        }

        @Override
        public Cell current() {
            return curr;
        }

        @Override
        public Cell previous() {
            curr = curr.board.getSquare(curr.row - 1, curr.col);
            return curr;
        }

        @Override
        public Cell next() {
            curr = curr.board.getSquare(curr.row + 1, curr.col);
            return curr;
        }
    }

    static class Horizontal implements CellIterator {

        private CellImpl curr;

        private Horizontal(CellImpl cell) {
            curr = cell;
        }

        @Override
        public boolean hasPrevious() {
            return curr.col > 0;
        }

        @Override
        public boolean hasNext() {
            return curr.col + 1 < curr.board.getColCount();
        }

        @Override
        public Cell current() {
            return curr;
        }

        @Override
        public Cell previous() {
            curr = curr.board.getSquare(curr.row, curr.col - 1);
            return curr;
        }

        @Override
        public Cell next() {
            curr = curr.board.getSquare(curr.row, curr.col + 1);
            return curr;
        }
    }

}
