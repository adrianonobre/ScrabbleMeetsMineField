package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.*;

/**
 * Created by adriano on 2017-01-05.
 */
public class LetterCollisionVisitor implements Visitor {
    @Override
    public void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }

    @Override
    public void visitSquare(Cell cell, PlayOutcome playOutcome) {
        if (cell.getPreviousContent() instanceof SquareContent.Letter
                    && !cell.getPreviousContent().equals(cell.getContent())) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "collides with existing word");
        } else {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
        }
    }
}
