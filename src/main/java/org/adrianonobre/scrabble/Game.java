package org.adrianonobre.scrabble;

import lddecker.board.impl.FancyBoard;
import org.adrianonobre.scrabble.visitor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by adriano on 2016-12-26.
 */
public class Game {

    private List<Visitor> visitors;
    private Board board;
    private int score;

    public Game(Set<String> dictWords) {
        dictWords = dictWords.stream().map(String::toUpperCase).collect(Collectors.toSet());

        visitors = new ArrayList<>();
        visitors.add(new LetterCollisionVisitor());
        visitors.add(new ValidWordVisitor(dictWords));
        visitors.add(new MineVisitor());
        visitors.add(new IntersectExistingWordVisitor());
    }

    public void start(int rowCount, int colCount) {
        this.board = new FancyBoard(rowCount, colCount);
        placeMines(board);
        score = 0;
    }

    public int getScore() {
        return score;
    }

    private void placeMines(Board board) {
        final int rows = board.getRowCount();
        final int cols = board.getColCount();

        final double mineRatio = 0.05; // the higher this number, the harder it is...

        for (int i = 0; i < rows * cols * mineRatio; i++) {
            int mine = (int) (Math.random() * rows * cols);

            final int row = mine / cols;
            final int col = mine % cols;

            final Cell cell = board.getSquare(row, col);
            cell.setContent(new SquareContent.Mine());
        }
    }

    public PlayOutcome play(int row, int col, Orientation orientation, String word) {

        word = word.toUpperCase();

        PlayOutcome playOutcome = executePlay(row, col, orientation, word);

        if (playOutcome.getOutcome() != PlayOutcome.OutcomeType.SUCCESS) {
            restoreBoardState(row, col, orientation, word);
        } else {
            score += playOutcome.getPlayScore();
        }

        return playOutcome;
    }

    private void restoreBoardState(int row, int col, Orientation orientation, String word) {
        Cell cell = board.getSquare(row, col);
        CellIterator iterator = orientation == Orientation.HORIZONTAL ? cell.horizontalIterator() : cell.verticalIterator();
        for (int i = 0; i < word.length(); i++) {
            final Cell sq = iterator.current();
            sq.restoreContent();
            if (iterator.hasNext()) {
                iterator.next();
            } else {
                break;
            }
        }
    }

    private PlayOutcome executePlay(int row, int col, Orientation orientation, String word) {
        PlayOutcome playOutcome = new PlayOutcome();

        SquareSequence squareSequence = BoardHelper.placeWordOnTheBoard(board, row, col, orientation, word, playOutcome);
        if (playOutcome.getOutcome() == PlayOutcome.OutcomeType.FAILURE) {
            return playOutcome;
        }

        for (Visitor visitor : visitors) {
            squareSequence.accept(visitor, playOutcome);
            if (playOutcome.getOutcome() == PlayOutcome.OutcomeType.FAILURE) {
                return playOutcome;
            }
        }

        Cell cell = board.getSquare(row, col);
        CellIterator iterator = orientation == Orientation.HORIZONTAL ? cell.horizontalIterator() : cell.verticalIterator();
        for (int i = 0; i < word.length(); i++) {
            final Cell sq = iterator.current();
            for (Visitor visitor : visitors) {
                sq.accept(visitor, playOutcome);
                if (playOutcome.getOutcome() == PlayOutcome.OutcomeType.FAILURE) {
                    return playOutcome;
                }
            }
            if (iterator.hasNext()) {
                iterator.next();
            }
        }

        return playOutcome;
    }

    public Board getBoard() {
        return board;
    }

}
