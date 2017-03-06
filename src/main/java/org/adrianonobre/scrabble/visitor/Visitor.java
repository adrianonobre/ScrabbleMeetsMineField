package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.Cell;
import org.adrianonobre.scrabble.PlayOutcome;
import org.adrianonobre.scrabble.SquareSequence;

/**
 * Created by adriano on 2017-01-05.
 */
public interface Visitor {

    void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome);

    void visitSquare(Cell cell, PlayOutcome playOutcome);
}
