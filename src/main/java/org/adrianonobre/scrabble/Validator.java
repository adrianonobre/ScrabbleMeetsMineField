package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2016-12-27.
 */
public interface Validator {

    Game.PlayOutcome validate(Board board, int row, int col, String word);


}
