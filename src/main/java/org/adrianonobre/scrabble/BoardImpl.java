package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2016-12-27.
 */
public class BoardImpl implements Board {

    private CellImpl[][] cells;

    public BoardImpl(int rowCount, int colCount) {
        this.cells = new CellImpl[rowCount][];

        for (int r = 0; r < rowCount; r++) {
            cells[r] = new CellImpl[colCount];

            for (int c = 0; c < colCount; c++) {
                cells[r][c] = new CellImpl(this, r, c);
            }
        }
    }

    @Override
    public int getRowCount() {
        return cells.length;
    }

    @Override
    public int getColCount() {
        return cells[0].length;
    }

    @Override
    public CellImpl getSquare(int row, int col) {
        return cells[row][col];
    }

}
