package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriano on 2017-01-05.
 */
public class IntersectExistingWordVisitor implements Visitor {
    @Override
    public void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome) {
        if (isFirstWordOnTheBoard(squareSequence)) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
            return;
        }

        for (Square square : squareSequence.getLetters()) {
            List<Square> neighbours = new ArrayList<>();
            neighbours.addAll(getNeighbours(square.horizontalIterator()));
            neighbours.addAll(getNeighbours(square.verticalIterator()));

            for (Square neighbour : neighbours) {
                if (!squareSequence.contains(neighbour)) {
                    if (SquareHelper.containsLetter(neighbour)) {
                        playOutcome.addScore(10);
                        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
                        return;
                    }
                }
            }
        }
        playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "does not intersect any other squareSequence on the board");
    }

    public boolean isFirstWordOnTheBoard(SquareSequence squareSequence) {
        final Board board = squareSequence.getLetters().get(0).getBoard();
        for (int r = 0; r < board.getRowCount(); r++) {
            for (int c = 0; c < board.getColCount(); c++) {
                final Square square = board.getSquare(r, c);
                if (SquareHelper.containsLetter(square) && !squareSequence.contains(square)) {
                    return false;
                }
            }
        }
        return true;
    }


    private List<Square> getNeighbours(Square.SquareIterator squareIterator) {
        List<Square> neighbours = new ArrayList<>();

        if (squareIterator.hasPrevious()) {
            neighbours.add(squareIterator.previous());
            squareIterator.next();
        }
        if (squareIterator.hasNext()) {
            neighbours.add(squareIterator.next());
        }
        return neighbours;
    }

    @Override
    public void visitSquare(Square square, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }
}
