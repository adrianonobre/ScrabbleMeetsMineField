package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.PlayOutcome;
import org.adrianonobre.scrabble.Square;
import org.adrianonobre.scrabble.SquareSequence;

/**
 * Created by adriano on 2017-01-05.
 */
public interface Visitor {

    void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome);

    void visitSquare(Square square, PlayOutcome playOutcome);
}
