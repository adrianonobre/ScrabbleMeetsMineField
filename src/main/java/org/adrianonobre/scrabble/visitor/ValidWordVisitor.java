package org.adrianonobre.scrabble.visitor;

import org.adrianonobre.scrabble.*;

import java.util.Set;

/**
 * Created by adriano on 2017-01-05.
 */
public class ValidWordVisitor implements Visitor {

    private final Set<String> knownWords;

    public ValidWordVisitor(Set<String> knownWords) {
        this.knownWords = knownWords;
    }

    @Override
    public void visitSquareSequence(SquareSequence squareSequence, PlayOutcome playOutcome) {
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }

    @Override
    public void visitSquare(Square square, PlayOutcome playOutcome) {
        if (!SquareHelper.containsLetter(square)) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "this might be a programming error");
            return;
        }

        int score = 0;
        Square.SquareIterator iterator = square.horizontalIterator();
        String word = getContinuousWord(iterator);
        if (word.length() > 1 && !knownWords.contains(word)) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "formed invalid word " + word);
            return;
        }
        score += word.length();

        iterator = square.verticalIterator();
        word = getContinuousWord(iterator);
        if (word.length() > 1 && !knownWords.contains(word)) {
            playOutcome.setOutcome(PlayOutcome.OutcomeType.FAILURE, "formed invalid word " + word);
            return;
        }
        score += word.length();

        playOutcome.addScore(score);
        playOutcome.setOutcome(PlayOutcome.OutcomeType.SUCCESS);
    }

    private String getContinuousWord(Square.SquareIterator iterator) {
        Square wordStart = null;
        while (iterator.hasPrevious()) {
            wordStart = iterator.previous();
            if (!SquareHelper.containsLetter(wordStart)) {
                wordStart = iterator.next();
                break;
            }
        }
        if (wordStart == null) {
            wordStart = iterator.current();
        }

        StringBuilder word = new StringBuilder("" + ((SquareContent.Letter) wordStart.getContent()).getLetter());
        while (iterator.hasNext()) {
            Square nextSquare = iterator.next();
            if (!SquareHelper.containsLetter(nextSquare)) {
                break;
            } else {
                word.append(((SquareContent.Letter) nextSquare.getContent()).getLetter());
            }
        }
        return word.toString();
    }

}
