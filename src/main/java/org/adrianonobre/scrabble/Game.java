package org.adrianonobre.scrabble;

import java.util.Set;

/**
 * Created by adriano on 2016-12-26.
 */
public class Game {

    public enum PlayOutcome {
        SUCCESS,
        INVALID_WORD,
        INVALID_NEW_WORD_FORMED,
        BOOM,
        OUT_OF_BOUNDS,
        COLLISION
    }

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    private final Set<String> dictWords;
    private Board board;
    private int score;

    public Game(Set<String> dictWords) {
        this.dictWords = dictWords;
    }

    public void start(int rowCount, int colCount) {
        this.board = new Board();
        board.init(rowCount, colCount);
        placeMines(board);
        score = 0;
    }

    public int getScore() {
        return score;
    }

    private void placeMines(Board board) {
        final int rows = board.getRowCount();
        final int cols = board.getColCount();

        for (int i = 0 ; i < rows * cols * 0.05; i++) {
            int mine = (int) (Math.random() * rows * cols);

            final int row = mine / cols;
            final int col = mine % cols;

            final Board.Square square = board.getSquare(row, col);
            square.setHasMine(true);
        }
    }

    public PlayOutcome play(int row, int col, Orientation orientation, String word) {
        word = word.toUpperCase();
        // validate
        if (!dictWords.contains(word)) {
            return PlayOutcome.INVALID_WORD;
        }

        int wordScore = 0;

        Board board = (Board) this.board.clone();

        Board.BoardIterator iterator = board.iterator(row, col, orientation);

        for (int i = 0; i < word.length(); i++) {
            if (iterator.hasNext()) {
                final Board.Square square = iterator.next();
                if (square.hasMine()) {
                    return PlayOutcome.BOOM;

                } else if (square.getCurrElement() != null &&
                            !square.getCurrElement().equals("" + word.charAt(i))) {
                    return PlayOutcome.COLLISION;
                }
                square.setCurrElement("" + word.charAt(i));

            } else {
                return PlayOutcome.OUT_OF_BOUNDS;
            }
        }
        // validates words formed by existing letters after the word
        String newWord = getContinuousWord(iterator);
        if (!dictWords.contains(newWord)) {
            return PlayOutcome.INVALID_NEW_WORD_FORMED;
        } else {
            wordScore += 10 + (newWord.length() - word.length());
        }

        Orientation reverseOrientation = orientation == Orientation.VERTICAL ? Orientation.HORIZONTAL : Orientation.VERTICAL;
        iterator = board.iterator(row, col, orientation);
        while (iterator.hasNext()) {
            final Board.Square square = iterator.next();
            if (square.getCurrElement() != null) {

                Board.BoardIterator reverseIterator = board.iterator(iterator.getCurrRow(), iterator.getCurrCol(), reverseOrientation);
                String intersectingWord = getContinuousWord(reverseIterator);
                if (intersectingWord.length() > 1) {
                   if (!dictWords.contains(intersectingWord)) {
                        return PlayOutcome.INVALID_NEW_WORD_FORMED;
                   } else {
                       wordScore += 5;
                   }
                }

            } else {
                break;
            }
        }

        score += wordScore;
        this.board = board;
        return PlayOutcome.SUCCESS;
    }

    private String getContinuousWord(Board.BoardIterator iterator) {

        while (iterator.hasPrevious()) {
            final Board.Square square = iterator.previous();
            if (square.getCurrElement() == null) {
                iterator.next();
                break;
            }
        }

        final StringBuilder builder = new StringBuilder(iterator.current().getCurrElement());

        while (iterator.hasNext()) {
            final Board.Square square = iterator.next();
            if (square.getCurrElement() != null) {
                builder.append(square.getCurrElement());
            } else {
                break;
            }
        }
        return builder.toString();
    }

    public Board getBoard() {
        return board;
    }

}
