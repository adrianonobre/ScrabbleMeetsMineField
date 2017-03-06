package lddecker.board;

public abstract class WordGame {
    protected AbstractCell[][] _board;
    protected boolean _gameIsOver = false;

    public abstract void playWord(String word, int column, int row, Direction direction) throws Exception;

    protected void updateBoardForNewWord(String word, int column, int row, Direction direction) throws Exception {
        word = word.toUpperCase();
        for (char c : word.toCharArray()) {
            AbstractCell abstractCell = _board[row][column];
            try {
                abstractCell.update(c);
            } catch (Exception e) {
                throw e;
            }
            if (Direction.DOWN == direction) {
                row++;
            } else if (Direction.ACROSS == direction) {
                column++;
            } else {
                throw new Exception("Unknown direction: " + direction.name());
            }
        }
    }

    public abstract void resetBoard();

    public int calculateScore() {
        int score = 0;
        for (AbstractCell[] rows : _board) {
            for (AbstractCell col : rows) {
                score += col.getScore();
            }
        }
        return score;
    }

    public abstract void endGame();

    public boolean isGameOver() {
        return _gameIsOver;
    }
}
