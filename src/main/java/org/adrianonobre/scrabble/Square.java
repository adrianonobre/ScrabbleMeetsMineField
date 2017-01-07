package org.adrianonobre.scrabble;

import org.adrianonobre.scrabble.visitor.Visitor;

/**
 * Created by adriano on 2017-01-05.
 */
public class Square {

    private final Board board;
    private final int row;
    private final int col;
    private SquareContent previousContent;
    private SquareContent content;
    private boolean hasMine;

    public Board getBoard() {
        return board;
    }

    public Square(Board board, int row, int col) {
        content = null;
        this.board = board;
        this.row = row;
        this.col = col;
    }

    public SquareContent getPreviousContent() {
        return previousContent;
    }

    public Object clone() {
        final Square clone = new Square(this.board, this.row, this.col);
        clone.previousContent = previousContent;
        clone.content = content;
        clone.hasMine = hasMine;
        return clone;
    }

    public void accept(Visitor visitor, PlayOutcome playOutcome) {
        visitor.visitSquare(this, playOutcome);
    }

    public SquareContent getContent() {
        return content;
    }

    public void restoreContent() {
        this.content = this.previousContent;
    }

    public void setContent(SquareContent content) {
        this.previousContent = this.content;
        this.content = content;
    }

    public SquareIterator.Horizontal horizontalIterator() {
        return new SquareIterator.Horizontal(this);
    }

    public SquareIterator.Vertical verticalIterator() {
        return new SquareIterator.Vertical(this);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public interface SquareIterator {

        boolean hasPrevious();

        boolean hasNext();

        Square current();

        Square previous();

        Square next();

        class Vertical implements SquareIterator {

            private Square curr;

            private Vertical(Square square) {
                curr = square;
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
            public Square current() {
                return curr;
            }

            @Override
            public Square previous() {
                curr = curr.board.getSquare(curr.row - 1, curr.col);
                return curr;
            }

            @Override
            public Square next() {
                curr = curr.board.getSquare(curr.row + 1, curr.col);
                return curr;
            }
        }

        class Horizontal implements SquareIterator {

            private Square curr;

            private Horizontal(Square square) {
                curr = square;
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
            public Square current() {
                return curr;
            }

            @Override
            public Square previous() {
                curr = curr.board.getSquare(curr.row, curr.col - 1);
                return curr;
            }

            @Override
            public Square next() {
                curr = curr.board.getSquare(curr.row, curr.col + 1);
                return curr;
            }
        }
    }
}
