package org.adrianonobre.visitor;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.adrianonobre.BoardTestHelper;
import org.adrianonobre.scrabble.*;
import org.adrianonobre.scrabble.visitor.ValidWordVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by adriano on 2017-01-07.
 */
@RunWith(JUnitParamsRunner.class)
public class ValidWordVisitorTest {

    private static final Set<String> KNOWN_WORDS = ImmutableSet.of("SCRABBLE", "GAME", "BED", "SOD");

    @SuppressWarnings("unused")
    private Iterable<Object[]> parametersForTest() {
        return ImmutableList.of(
                new Object[]{
                        new String[]{
                                "   G    ",
                                "SCRABBLE",
                                "   M    ",
                                "   E    "},
                        1, 3, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "   G    ",
                                "SCRABBLE",
                                "   M    ",
                                "   E    "},
                        0, 3, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "   G    ",
                                "SCRABBLE",
                                "   M    ",
                                "   E    "},
                        0, 0, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "   A    ",
                                "SCRABBLE",
                                "   A    ",
                                "   A    "},
                        1, 3, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "   G    ",
                                "AAAAAAAA",
                                "   M    ",
                                "   E    "},
                        1, 3, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "   A    ",
                                "SCRABBLE",
                                "   A    ",
                                "   A    "},
                        1, 0, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "       B",
                                "SCRABBLE",
                                "       D"},
                        1, 7, PlayOutcome.OutcomeType.SUCCESS},

                new Object[]{
                        new String[]{
                                "       E",
                                "SCRABBLE",
                                "       E"},
                        1, 7, PlayOutcome.OutcomeType.FAILURE},

                new Object[]{
                        new String[]{
                                "SCRABBLE",
                                "O      E",
                                "D      E"},
                        0, 0, PlayOutcome.OutcomeType.SUCCESS}
        );
    }

    @Test
    @Parameters()
    public void test(String[] boardState, int row, int col, PlayOutcome.OutcomeType expectedOutcome) {
        final Board board = BoardTestHelper.getBoardWithSetState(boardState);

        PlayOutcome playOutcome = new PlayOutcome();
        final Square square = board.getSquare(row, col);
        square.accept(new ValidWordVisitor(KNOWN_WORDS), playOutcome);

        Assert.assertEquals(expectedOutcome, playOutcome.getOutcome());
    }

}
