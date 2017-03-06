package org.adrianonobre.scrabble;

import org.adrianonobre.scrabble.visitor.Visitor;

/**
 * Created by adriano on 2017-03-05.
 */
public interface Cell {
    Board getBoard();

    SquareContent getPreviousContent();

    Cell clone();

    void accept(Visitor visitor, PlayOutcome playOutcome);

    SquareContent getContent();

    void restoreContent();

    void setContent(SquareContent content);

    CellIterator horizontalIterator();

    CellIterator verticalIterator();

    int getRow();

    int getCol();
}
