package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-03-05.
 */
public interface Board {
    int getRowCount();

    int getColCount();

    Cell getSquare(int row, int col);
}
