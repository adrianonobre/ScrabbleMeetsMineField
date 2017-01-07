package org.adrianonobre.visitor;

import com.google.common.collect.ImmutableList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.adrianonobre.BoardTestHelper;
import org.adrianonobre.scrabble.*;
import org.adrianonobre.scrabble.visitor.IntersectExistingWordVisitor;
import org.adrianonobre.scrabble.visitor.ValidWordVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by adriano on 2017-01-07.
 */
@RunWith(JUnitParamsRunner.class)
public class IntersectExistingWordVisitorTest {

    @SuppressWarnings("unused")
    private Iterable<Object[]> parametersForTest() {
        return ImmutableList.of(
                new Object[]{
                        new String[]{
                                "SCRABBLE",
                                "        ",
                                "        "},
                        "ADD", 0, 3, Orientation.VERTICAL, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "SCRABBLE",
                                "        ",
                                "        "},
                        "DD", 1, 3, Orientation.VERTICAL, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "SCRABBLE",
                                "        ",
                                "        "},
                        "A", 2, 3, Orientation.VERTICAL, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "SCRA    ",
                                "        ",
                                "        "},
                        "BBLE", 0, 4, Orientation.HORIZONTAL, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "SCRA    ",
                                "        ",
                                "        "},
                        "BBLE", 1, 4, Orientation.HORIZONTAL, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "SCRA    ",
                                "        ",
                                "        "},
                        "BBLE", 1, 3, Orientation.HORIZONTAL, PlayOutcome.OutcomeType.SUCCESS}

        );

    }

    @Test
    @Parameters()
    public void test(String[] boardState, String word, int row, int col, Orientation orientation, PlayOutcome.OutcomeType expectedOutcome) {
        final Board board = BoardTestHelper.getBoardWithSetState(boardState);

        PlayOutcome playOutcome = new PlayOutcome();
        final SquareSequence squareSequence = BoardHelper.placeWordOnTheBoard(board, row, col, orientation, word, playOutcome);
        Assert.assertEquals(PlayOutcome.OutcomeType.SUCCESS, playOutcome.getOutcome());

        squareSequence.accept(new IntersectExistingWordVisitor(), playOutcome);

        Assert.assertEquals(expectedOutcome, playOutcome.getOutcome());
    }
}