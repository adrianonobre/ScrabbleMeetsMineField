package org.adrianonobre.scrabble;

import org.adrianonobre.scrabble.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriano on 2017-01-05.
 */
public class SquareSequence {

    private List<Cell> letters = new ArrayList<>();

    public void addLetter(Cell cell) {
        letters.add(cell);
    }

    public int length() {
        return letters.size();
    }

    public List<Cell> getLetters() {
        return letters;
    }

    public boolean contains(Cell cell) {
        for (Cell letter : letters) {
            if (letter.getRow() == cell.getRow() && letter.getCol() == cell.getCol()) {
                return true;
            }
        }

        return false;
    }

    public void accept(Visitor visitor, PlayOutcome playOutcome) {
        visitor.visitSquareSequence(this, playOutcome);
    }
}
