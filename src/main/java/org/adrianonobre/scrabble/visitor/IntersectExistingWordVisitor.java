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

        for (Cell cell : squareSequence.getLetters()) {
            List<Cell> neighbours = new ArrayList<>();
            neighbours.addAll(getNeighbours(cell.horizontalIterator()));
            neighbours.addAll(getNeighbours(cell.verticalIterator()));

            for (Cell neighbour : neighbours) {
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
        final BoardImpl board = squareSequence.getLetters().get(0).getBoard();
        for (int r = 0; r < board.getRowCount(); r++) {
            for (int c = 0; c < board.getColCount(); c++) {
                final Cell cell = board.getSquare(r, c);
                if (SquareHelper.containsLetter(cell) && !squareSequence.contains(cell)) {
                    return false;
                }
            }
        }
        return true;
    }


    private List<Cell> getNeighbours(CellIterator cellIterator) {
        List<Cell> neighbours = new ArrayList<>();

        if (cellIterator.hasPrevious()) {
            neighbours.add(cellIterator.previous());
            cellIterator.next();
        }
        if (cellIterator.hasNext()) {
            neighbours.add(cellIterator.next());
        }
        return neighbours;
    }

    @Override
    public void visitSquare(Cell cell, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }
}
