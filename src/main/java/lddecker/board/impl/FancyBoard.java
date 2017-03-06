package lddecker.board.impl;

import lddecker.board.AbstractCell;
import lddecker.board.Direction;
import org.adrianonobre.scrabble.Board;
import org.adrianonobre.scrabble.CellImpl;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FancyBoard implements Board {
    private static final String COLUMN_LABELS = "ABCDEFGHIJKLMOP";

    private final JPanel _gui = new JPanel(new BorderLayout(3, 3));
    private final Color _backgroundColour = Color.LIGHT_GRAY;
    private final int _cellsInBoard = 15;
    private final Color _borderColour = Color.BLACK;
    private final int _minimumSize = 400;
    private final int _height;
    private final int _width;
    private final JLabel _message = new JLabel("Make your move!");
    private final JComboBox _directionBox = new JComboBox<>(Direction.values());

    protected AbstractCell[][] _board;

    private JTextArea _wordInput = new JTextArea("");
    private JPanel _gameBoard;


    public FancyBoard(int height, int width) {
        _height = height;
        _width = width;
        _board = new FancyCell[_height][_width];
        initializeBoard();
    }

    private void initializeBoard() {
        // set up the main GUI
        _gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        _gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                _message.setText("Make your move!");
//                resetBoard();
            }
        };

        Action scoreAction = new AbstractAction("Score") {

            @Override
            public void actionPerformed(ActionEvent e) {
                int score = 0;
//                int score = calculateScore();

                _message.setText("Score: " + score);
            }
        };

        tools.add(newGameAction);
        tools.add(scoreAction);
        tools.addSeparator();
        tools.add(_wordInput);
        tools.addSeparator();
        tools.add(_directionBox);
        tools.addSeparator();
        tools.add(_message);


        _gui.add(new JLabel("?"), BorderLayout.LINE_START);

        _gameBoard = new JPanel(new GridLayout(0, _cellsInBoard + 1)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstraint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension dimension = new Dimension(_minimumSize, _minimumSize);
                Dimension prefSize;
                Component component = getParent();
                if (component == null) {
                    prefSize = new Dimension((int) dimension.getWidth(), (int) dimension.getHeight());
                } else if (component.getWidth() > dimension.getWidth() && component.getHeight() > dimension.getHeight()) {
                    prefSize = component.getSize();
                } else {
                    prefSize = dimension;
                }
                int width = (int) prefSize.getWidth();
                int height = (int) prefSize.getHeight();
                int smallerSize = (width > height ? height : width);
                return new Dimension(smallerSize, smallerSize);
            }
        };
        _gameBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(_borderColour)
        ));

        _gameBoard.setBackground(_backgroundColour);
        JPanel boardConstraint = new JPanel(new GridBagLayout());
        boardConstraint.setBackground(_backgroundColour);
        boardConstraint.add(_gameBoard);
        _gui.add(boardConstraint);

        createBoardSquares();
        initBoardDog();
    }

    private void createBoardSquares() {
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                AbstractCell button = new FancyCell(this);
                final int finalI = i;
                final int finalJ = j;
                AbstractAction cellSelectAction = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String inputWord = _wordInput.getText();
                        _wordInput.setText("");
                        Direction selectedDirection = (Direction) _directionBox.getSelectedItem();
                        try {
//                            playWord(inputWord, finalJ, finalI, selectedDirection);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                };
                ((FancyCell) button).setAction(cellSelectAction);
                _board[i][j] = button;
            }
        }

        _gameBoard.add(new JLabel(""));
        // fill the top label
        for (int i = 0; i < _cellsInBoard; i++) {
            _gameBoard.add(new JLabel(COLUMN_LABELS.substring(i, i + 1), SwingConstants.CENTER));
        }
        // fill the rest of the rows
        for (int row = 0; row < _cellsInBoard; row++) {
            for (int column = 0; column < _cellsInBoard; column++) {
                switch (column) {
                    case 0:
                        _gameBoard.add(new JLabel("" + row, SwingConstants.CENTER));
                    default:
                        _gameBoard.add(((FancyCell) _board[row][column]).getButton());
                }
            }
        }
    }

    public final JComponent getGui() {
        return _gui;
    }

    private void initBoardDog() {
        Color beige = new Color(210, 180, 140);
        Color black = Color.GRAY;
        Color red = Color.RED;
        getButtonAtLocation(2, 6).doubleScoreModifier(beige);
        getButtonAtLocation(2, 7).doubleScoreModifier(beige);
        getButtonAtLocation(2, 8).doubleScoreModifier(beige);
        getButtonAtLocation(2, 9).tripleScoreModifier(black);

        getButtonAtLocation(3, 3).tripleScoreModifier(black);
        getButtonAtLocation(3, 4).doubleScoreModifier(beige);
        getButtonAtLocation(3, 5).doubleScoreModifier(beige);
        getButtonAtLocation(3, 6).tripleScoreModifier(black);
        getButtonAtLocation(3, 7).doubleScoreModifier(beige);
        getButtonAtLocation(3, 8).tripleScoreModifier(black);
        getButtonAtLocation(3, 9).tripleScoreModifier(black);
        getButtonAtLocation(3, 10).tripleScoreModifier(black);

        getButtonAtLocation(4, 4).doubleScoreModifier(beige);
        getButtonAtLocation(4, 5).doubleScoreModifier(beige);
        getButtonAtLocation(4, 6).doubleScoreModifier(beige);
        getButtonAtLocation(4, 7).doubleScoreModifier(beige);
        getButtonAtLocation(4, 8).tripleScoreModifier(black);
        getButtonAtLocation(4, 9).tripleScoreModifier(black);
        getButtonAtLocation(4, 10).tripleScoreModifier(black);

        getButtonAtLocation(5, 6).tripleScoreModifier(red);
        getButtonAtLocation(5, 7).tripleScoreModifier(red);
        getButtonAtLocation(5, 8).tripleScoreModifier(black);
        getButtonAtLocation(5, 9).tripleScoreModifier(black);
        getButtonAtLocation(5, 10).tripleScoreModifier(black);

        getButtonAtLocation(6, 6).doubleScoreModifier(beige);
        getButtonAtLocation(6, 7).doubleScoreModifier(beige);
        getButtonAtLocation(6, 8).doubleScoreModifier(beige);
        getButtonAtLocation(6, 9).tripleScoreModifier(black);

        getButtonAtLocation(7, 4).doubleScoreModifier(beige);
        getButtonAtLocation(7, 5).doubleScoreModifier(beige);
        getButtonAtLocation(7, 6).doubleScoreModifier(beige);
        getButtonAtLocation(7, 7).doubleScoreModifier(beige);
        getButtonAtLocation(7, 8).doubleScoreModifier(beige);

        getButtonAtLocation(8, 6).doubleScoreModifier(beige);
        getButtonAtLocation(8, 7).doubleScoreModifier(beige);
        getButtonAtLocation(8, 8).doubleScoreModifier(beige);

        getButtonAtLocation(9, 6).doubleScoreModifier(beige);
        getButtonAtLocation(9, 7).doubleScoreModifier(beige);
        getButtonAtLocation(9, 8).doubleScoreModifier(beige);
        getButtonAtLocation(9, 9).doubleScoreModifier(beige);

        getButtonAtLocation(10, 6).doubleScoreModifier(beige);
        getButtonAtLocation(10, 7).doubleScoreModifier(beige);
        getButtonAtLocation(10, 8).doubleScoreModifier(beige);
        getButtonAtLocation(10, 9).doubleScoreModifier(beige);

        getButtonAtLocation(11, 4).doubleScoreModifier(beige);
        getButtonAtLocation(11, 5).doubleScoreModifier(beige);
        getButtonAtLocation(11, 6).doubleScoreModifier(beige);
        getButtonAtLocation(11, 7).doubleScoreModifier(beige);
        getButtonAtLocation(11, 8).doubleScoreModifier(beige);
        getButtonAtLocation(11, 9).doubleScoreModifier(beige);
        getButtonAtLocation(11, 10).doubleScoreModifier(beige);
        getButtonAtLocation(11, 11).doubleScoreModifier(beige);

    }

    private AbstractCell getButtonAtLocation(int row, int column) {
        return _board[row][column];
    }

    @Override
    public int getRowCount() {
        return _height;
    }

    @Override
    public int getColCount() {
        return _width;
    }

    @Override
    public CellImpl getSquare(int row, int col) {
        return null;
    }
}
