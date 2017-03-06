package lddecker.board.impl;

import lddecker.board.AbstractCell;
import org.adrianonobre.scrabble.*;
import org.adrianonobre.scrabble.visitor.Visitor;

import javax.swing.*;
import java.awt.*;

public class FancyCell extends AbstractCell implements Cell {
    private JButton _button;

    private final FancyBoard _board;
    private final Insets _buttonMargin = new Insets(0, 0, 0, 0);
    private final Color _defaultCellBackground = Color.WHITE;

    public FancyCell(FancyBoard board) {
        _board = board;
        _button = new JButton();
        _button.setMargin(_buttonMargin);
        _button.setBackground(_defaultCellBackground);
        _button.setText("");
    }

    public JButton getButton() {
        return _button;
    }

    @Override
    protected void updateCellValue(Character newVal) {
        _button.setText(newVal.toString());
    }

    @Override
    protected void resetImpl() {
        _button.setText("");
    }

    @Override
    protected void tripleScoreModifierImpl() {
        _button.setBackground(Color.RED);
    }

    @Override
    protected void doubleScoreModifierImpl() {
        _button.setBackground(Color.PINK);
    }

    @Override
    public void doubleScoreModifier(Object modifier) {
        super.doubleScoreModifier(modifier);
        _button.setBackground((Color) modifier);
    }

    @Override
    public void tripleScoreModifier(Object modifier) {
        super.tripleScoreModifier(modifier);
        _button.setBackground((Color) modifier);
    }

    public void setAction(Action action) {
        _button.setAction(action);
    }

    public Action getAction() {
        return _button.getAction();
    }

    @Override
    public Board getBoard() {
        return _board;
    }

    @Override
    public SquareContent getPreviousContent() {
        return null;
    }

    @Override
    public void accept(Visitor visitor, PlayOutcome playOutcome) {

    }

    @Override
    public SquareContent getContent() {
        return null;
    }

    @Override
    public void restoreContent() {

    }

    @Override
    public void setContent(SquareContent content) {

    }

    @Override
    public CellIterator horizontalIterator() {
        return null;
    }

    @Override
    public CellIterator verticalIterator() {
        return null;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getCol() {
        return 0;
    }

    public Cell clone() {
        return null;
    }
}
