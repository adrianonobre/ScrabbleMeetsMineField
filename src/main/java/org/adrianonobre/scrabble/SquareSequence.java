package org.adrianonobre.scrabble;

import org.adrianonobre.scrabble.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriano on 2017-01-05.
 */
public class SquareSequence {

    private List<Square> letters = new ArrayList<>();

    public void addLetter(Square square) {
        letters.add(square);
    }

    public int length() {
        return letters.size();
    }

    public List<Square> getLetters() {
        return letters;
    }

    public boolean contains(Square square) {
        for (Square letter : letters) {
            if (letter.getRow() == square.getRow() && letter.getCol() == square.getCol()) {
                return true;
            }
        }

        return false;
    }

    public void accept(Visitor visitor, PlayOutcome playOutcome) {
        visitor.visitSquareSequence(this, playOutcome);
    }
}
