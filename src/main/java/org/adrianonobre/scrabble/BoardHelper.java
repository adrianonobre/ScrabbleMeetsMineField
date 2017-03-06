package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-07.
 */
public class BoardHelper {
    public static SquareSequence placeWordOnTheBoard(Board board, int row, int col, Orientation orientation, String word, PlayOutcome playOutcome) {
        Cell cell = board.getSquare(row, col);
        SquareSequence squareSequence = new SquareSequence();
        CellIterator iterator = orientation == Orientation.HORIZONTAL ? cell.horizontalIterator() : cell.verticalIterator();
        for (int i = 0; i < word.length(); i++) {
            cell = iterator.current();
            cell.setContent(new SquareContent.Letter(word.charAt(i)));
            squareSequence.addLetter(cell);
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
