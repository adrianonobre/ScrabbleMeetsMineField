package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.PlayOutcome;
import org.adrianonobre.scrabble.Square;
import org.adrianonobre.scrabble.SquareContent;
import org.adrianonobre.scrabble.SquareSequence;

/**
 * Created by adriano on 2017-01-05.
 */
public class LetterCollisionVisitor implements Visitor {
    @Override
    public void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }

    @Override
    public void visitSquare(Square square, PlayOutcome playOutcome) {
        if (square.getPreviousContent() instanceof SquareContent.Letter
                    && !square.getPreviousContent().equals(square.getContent())) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "collides with existing word");
        } else {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
        }
    }
}
