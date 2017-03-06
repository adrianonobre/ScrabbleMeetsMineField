package lddecker.board;

public abstract class AbstractCell {
    private int _multiplier;
    private Character _emptyDisplay;
    private Character _currValue;

    protected AbstractCell() {
        _multiplier = 1;
        _emptyDisplay = ' ';
    }

    public void update(Character newVal) throws Exception {
        if (!isValidCharacter(newVal)) {
            throw new Exception("Invalid value for cell: [" + newVal + "]");
        }
        if (_currValue != null && !_currValue.equals(newVal)) {
            throw new Exception("CellImpl is already populated");
        }
        _currValue = newVal;
        updateCellValue(newVal);
    }

    protected abstract void updateCellValue(Character newVal);

    public boolean isValidCharacter(Character newVal) {
        return null != newVal && ' ' != newVal;
    }

    public void reset(){
        _currValue = null;
        resetImpl();
    }

    protected abstract void resetImpl();

    public void doubleScoreModifier() {
        _multiplier = 2;
        doubleScoreModifierImpl();
    }

    public void tripleScoreModifier() {
        _multiplier = 3;
        tripleScoreModifierImpl();
    }

    protected abstract void tripleScoreModifierImpl();

    public void doubleScoreModifier(Object modifier) {
        _multiplier = 2;
    }

    public void tripleScoreModifier(Object modifier) {
        _multiplier = 3;
    }

    protected abstract void doubleScoreModifierImpl();

    public int getScore() {
        if (null == _currValue) {
            return 0;
        }
        return _multiplier;
    }

    public Character getDisplayChar() {
        if (null == _currValue) {
            return _emptyDisplay;
        }
        return _currValue;
    }
}
