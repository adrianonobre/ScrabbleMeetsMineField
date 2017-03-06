package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.*;

/**
 * Created by adriano on 2017-01-05.
 */
public class MineVisitor implements Visitor {
    @Override
    public void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }

    @Override
    public void visitSquare(Cell cell, PlayOutcome playOutcome) {
        if (cell.getPreviousContent() instanceof SquareContent.Mine) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "Boom!!");
        }
    }
}
