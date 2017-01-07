package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-07.
 */
public class BoardHelper {
    public static SquareSequence placeWordOnTheBoard(Board board, int row, int col, Orientation orientation, String word, PlayOutcome playOutcome) {
        Square square = board.getSquare(row, col);
        SquareSequence squareSequence = new SquareSequence();
        Square.SquareIterator iterator = orientation == Orientation.HORIZONTAL ? square.horizontalIterator() : square.verticalIterator();
        for (int i = 0; i < word.length(); i++) {
            square = iterator.current();
            square.setContent(new SquareContent.Letter(word.charAt(i)));
            squareSequence.addLetter(square);
            if (iterator.hasNext()) {
                iterator.next();
            } else {
                break;
            }
        }
        if (squareSequence.length() < word.length()) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "out of bounds");
        } else {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
        }

        return squareSequence;
    }
}
